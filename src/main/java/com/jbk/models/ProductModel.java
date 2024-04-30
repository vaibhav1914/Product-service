package com.jbk.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ProductModel {

	private long productId;

	@Pattern(regexp = "^(?!\\d).*\\S$", message = "Product name cannot be null, blank, or start with a numeric character")
	private String productName;
	@NotNull
	@Min(value = 1)
	private double productCost;

	private long categoryId;
	private long supplierId;

	@NotNull
	@Min(value = 1, message = "Product qty greter than 1 allowed")
	private int productQty;
	@NotNull
	@Min(value = 0, message = "Delivery charges 0 or greter than 0 allowed")
	private int dileveryCharge;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductCost() {
		return productCost;
	}
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public int getDileveryCharge() {
		return dileveryCharge;
	}
	public void setDileveryCharge(int dileveryCharge) {
		this.dileveryCharge = dileveryCharge;
	}
	public ProductModel(long productId,
			@Pattern(regexp = "^(?!\\d).*\\S$", message = "Product name cannot be null, blank, or start with a numeric character") String productName,
			@NotNull @Min(1) double productCost, long categoryId, long supplierId,
			@NotNull @Min(value = 1, message = "Product qty greter than 1 allowed") int productQty,
			@NotNull @Min(value = 0, message = "Delivery charges 0 or greter than 0 allowed") int dileveryCharge) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.categoryId = categoryId;
		this.supplierId = supplierId;
		this.productQty = productQty;
		this.dileveryCharge = dileveryCharge;
	}
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", productQty=" + productQty
				+ ", dileveryCharge=" + dileveryCharge + "]";
	}

	
}
