package com.jbk.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.models.Pr_Ca_Su;
import com.jbk.models.ProductModel;
import com.jbk.services.ProductService;

@RestController
@RequestMapping("product")
public class ProductControllers {

	@Autowired
	private ProductService service;
	
	
	@PostMapping("/add-product")
	public ResponseEntity<String> addProduct(@RequestBody @Valid ProductModel productModel) 
	{
		service.addProduct(productModel);
		return ResponseEntity.ok("Added!!!");
	}
	
	@GetMapping("/get-product-by-name")
	public ResponseEntity<ProductModel> getProductByName(@RequestParam String productName) 
	{
		return ResponseEntity.ok(service.getProductByName(productName));
	}
	
	@GetMapping("/get-product-by-id")
	public ResponseEntity<ProductModel> getProductById(@RequestParam long productId) 
	{
		return ResponseEntity.ok(service.getProductById(productId));

	}
	
	@PostMapping("/upload-excel-sheet")
	public ResponseEntity<Map<String, Object>> uploadExcelSheet(@RequestParam MultipartFile excelSheet) 
	{
	
		return ResponseEntity.ok(service.uploadExcelSheet(excelSheet));
	}
	
	@GetMapping("/get-pr-ca-su")
	public ResponseEntity<Pr_Ca_Su> getProductCaSup(@RequestParam long productId) 
	{
		return ResponseEntity.ok(service.getPrCaSu(productId));
	}
}
