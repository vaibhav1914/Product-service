package com.jbk.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.exceptions.ResourceNotFound;
import com.jbk.exceptions.SomethingWentWrong;

import com.jbk.models.ProductModel;
import com.jbk.models.SupplierModel;


@Component
public class ObjectValidation {

	

	public Map<String, String> excelSheetValidation(ProductModel productModel) {
		Map<String, String> validationMap = new HashMap<>();

		String productName = productModel.getProductName();

		//long supplierId = productModel.getSupplier().getSupplierId();
		
		
		
		
		//long categoryId = productModel.getCategory().getCategoryId();

		if (productModel.getProductCost() <= 0) {
			validationMap.put("Product Cost", "Invalid Product Cost");
		}
		if (productName.isEmpty() || productName.trim().equals("")) {
			validationMap.put("Product Name", "Invalid Product Name");

		}
		if (productModel.getDileveryCharge() < 0) {
			validationMap.put("Delivery Charges", "Invalid Delivery Charges");

		}
		if (productModel.getProductQty() < 0) {
			validationMap.put("Product Quantity", "Invalid Product Quantity");

		}
//		if (supplierId >=0 ) {
//			try {
//				 //supplierService.getSupplierById(supplierId);
//				//System.out.println("SUpplier       "+supplierById);
//				
//			} 
//			catch (ResourceNotFound e) {
//				validationMap.put("Supplier", e.getMessage());
//			} catch (SomethingWentWrong e) {
//				validationMap.put("Supplier", e.getMessage());
//			}
//		} else {
//			validationMap.put("Supplier", "Invalid Supplier Id");
//		}
//
//		if (categoryId > 0) {
//			try {
//				//categoryService.getCategoryById(categoryId);
//
//			}catch(NullPointerException e ) 
//			{
//				validationMap.put("Supplier", e.getMessage());
//				
//			}
//			catch (ResourceNotFound e) {
//				validationMap.put("Supplier", e.getMessage());
//			} catch (SomethingWentWrong e) {
//				validationMap.put("Supplier", e.getMessage());
//			}
//		} else {
//			validationMap.put("Supplier", "Invalid Supplier Id");
//		}
		//System.out.println("Mapppp"+validationMap);
		return validationMap;

	}

}
