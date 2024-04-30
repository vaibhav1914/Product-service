package com.jbk.dao;

import com.jbk.entity.ProductEntity;

public interface ProductDao {

	public boolean addProduct(ProductEntity productEntity);
	public ProductEntity getProductByName(String productName);
	public ProductEntity getProductById(long productId);

	
}
