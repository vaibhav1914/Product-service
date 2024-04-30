package com.jbk.services;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.models.Pr_Ca_Su;
import com.jbk.models.ProductModel;

public interface ProductService {
	public boolean addProduct(ProductModel productModel);
	public ProductModel getProductByName(String productName);
	public ProductModel getProductById(long productId);
	public Map<String, Object> uploadExcelSheet(MultipartFile excelSheet);
	
	public Pr_Ca_Su getPrCaSu(long productId);
}
