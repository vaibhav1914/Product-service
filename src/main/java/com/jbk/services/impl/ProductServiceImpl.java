package com.jbk.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exceptions.ResourceNotFound;
import com.jbk.exceptions.SomethingWentWrong;
import com.jbk.models.CategoryModel;
import com.jbk.models.Pr_Ca_Su;
import com.jbk.models.ProductModel;
import com.jbk.models.SupplierModel;
import com.jbk.services.ProductService;
import com.jbk.utility.ObjectValidation;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectValidation validation;

	@Autowired
	private RestTemplate restTemplate;
	Map<String, String> excelSheetValidation = new HashMap<>();
	Map<Integer, Map<String, String>> rowAndErrorMap = new HashMap<>();
	List<Integer> existRows = new ArrayList<Integer>();

	Map<String, Object> finalMap = new HashMap<String, Object>();

	@Override
	public boolean addProduct(ProductModel productModel) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		productModel.setProductId(Long.parseLong(timeStamp));

		return dao.addProduct(modelMapper.map(productModel, ProductEntity.class));
	}

	@Override
	public ProductModel getProductByName(String productName) {

		ProductEntity productByName = dao.getProductByName(productName);
		if (productByName != null) {
			return modelMapper.map(productByName, ProductModel.class);
		} else {
			// throw new ResourceNotFound("Product Not Found For Name : " + productName);
			return null;
		}
	}

	@Override
	public ProductModel getProductById(long productId) {
		try {
			ProductEntity productById = dao.getProductById(productId);
			if (productById != null) {
				return modelMapper.map(productById, ProductModel.class);
			} else {
				throw new ResourceNotFound("Product Not Found For ID : " + productId);

			}

		} catch (ResourceNotFound e) {
			throw new ResourceNotFound(e.getMessage());
		}

	}

	@Override
	public Map<String, Object> uploadExcelSheet(MultipartFile excelSheet) {
		int isAddedCounter = 0;
		int issueCounter = 0;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					"excelsheet" + File.separator + excelSheet.getOriginalFilename());
			fileOutputStream.write(excelSheet.getBytes());
			List<ProductModel> excelSheet2 = readExcelSheet(
					"excelsheet" + File.separator + excelSheet.getOriginalFilename());
			for (ProductModel productModel : excelSheet2) {
				ProductEntity productEntity = modelMapper.map(productModel, ProductEntity.class);
				try {
					boolean isAdded = dao.addProduct(productEntity);
					if (isAdded) {
						isAddedCounter = isAddedCounter + 1;
					}
				} catch (SomethingWentWrong e) {
					issueCounter = issueCounter + 1;
				}
			}
			finalMap.put("Added in DB Records", isAddedCounter);
			finalMap.put("Not Added Records", existRows.size());
			finalMap.put("Errors In Records", rowAndErrorMap);
			finalMap.put("Already Exist Records List", existRows);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalMap;
	}

	public List<ProductModel> readExcelSheet(String file) {
		int rowNum = 0;
		List<ProductModel> modelList = new ArrayList<>();
		try {
			// file
			FileInputStream fileInputStream = new FileInputStream(file);
			// workbook
			Workbook workbook = WorkbookFactory.create(fileInputStream);
			// sheet
			Sheet sheetAt = workbook.getSheetAt(0);
			// rows
			Iterator<Row> rowIterator = sheetAt.rowIterator();

			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				ProductModel productModel = new ProductModel();
				String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
				productModel.setProductId(Long.parseLong(timeStamp + rowNum));

				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					if (cell.getCellType() == CellType.BLANK) {
						continue;
					}
					switch (cell.getColumnIndex()) {
					case 0: {
						productModel.setProductName(cell.getStringCellValue());
						break;
					}
					case 1: {
						SupplierModel supplierModel = new SupplierModel();
						supplierModel.setSupplierId((long) cell.getNumericCellValue());
						// productModel.setSupplier(supplierModel);
						break;
					}
					case 2: {
						CategoryModel category = new CategoryModel();
						category.setCategoryId((long) cell.getNumericCellValue());
						// productModel.setCategory(category);
						break;
					}
					case 3: {
						productModel.setProductQty((int) cell.getNumericCellValue());

						break;
					}
					case 4: {
						productModel.setProductCost(cell.getNumericCellValue());
						break;
					}
					case 5: {
						productModel.setDileveryCharge((int) cell.getNumericCellValue());
						break;
					}
					}
				}
				/// System.out.println(productModel);
				excelSheetValidation = validation.excelSheetValidation(productModel);

				if (!excelSheetValidation.isEmpty()) {
					rowAndErrorMap.put(rowNum + 1, excelSheetValidation);
				} else {
					// System.out.println(1);
					ProductModel productByName = getProductByName(productModel.getProductName());
					if (productByName == null) {

						modelList.add(productModel);
					} else {

						existRows.add(rowNum + 1);
					}
				}

			}
			// System.out.println("Total: "+modelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelList;
	}

	@Override
	public Pr_Ca_Su getPrCaSu(long productId) {
		ProductModel productById = null;
		Pr_Ca_Su pcs = new Pr_Ca_Su();

		try {
			productById = getProductById(productId);
			if (productById != null) {
				pcs.setProductModel(productById);
			}

		} catch (Exception e) {

		}

		if (productById != null) {
			try {
				long supplierId = productById.getSupplierId();
				SupplierModel supplier = restTemplate.getForObject(
						"http://localhost:8080/supplier/get-supplier-by-id?supplierId=" + supplierId,
						SupplierModel.class);
				if (supplier != null) {
					pcs.setSupplierModel(supplier);
				} else {
					pcs.setSupplierModel(null);
				}
			} catch (ResourceAccessException e) {
				pcs.setSupplierModel(null);
			}
			try {
				long categoryId = productById.getCategoryId();

				CategoryModel category = restTemplate.getForObject(
						"http://localhost:8081/category/get-category-by-id?categoryId=" + categoryId,
						CategoryModel.class);
				if (category != null) {
					pcs.setCategoryModel(category);
				}

			} catch (ResourceAccessException e) {
				pcs.setCategoryModel(null);
			} catch (NullPointerException e) {
				pcs.setCategoryModel(null);
			}

		}
		return pcs;
	}

}
