package com.jbk.models;

public class Pr_Ca_Su {
	
	private ProductModel productModel;
	private CategoryModel categoryModel;
	private SupplierModel supplierModel;
	public ProductModel getProductModel() {
		return productModel;
	}
	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}
	public CategoryModel getCategoryModel() {
		return categoryModel;
	}
	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	public SupplierModel getSupplierModel() {
		return supplierModel;
	}
	public void setSupplierModel(SupplierModel supplierModel) {
		this.supplierModel = supplierModel;
	}
	public Pr_Ca_Su(ProductModel productModel, CategoryModel categoryModel, SupplierModel supplierModel) {
		super();
		this.productModel = productModel;
		this.categoryModel = categoryModel;
		this.supplierModel = supplierModel;
	}
	public Pr_Ca_Su() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Pr_Ca_Su [productModel=" + productModel + ", categoryModel=" + categoryModel + ", supplierModel="
				+ supplierModel + "]";
	}
	

}
