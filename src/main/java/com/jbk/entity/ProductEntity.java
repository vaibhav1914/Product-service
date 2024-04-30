package com.jbk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductEntity {
	@Id
	private long productId;
	private String productName;
	private double productCost;

	private long supplierId;

	private long categoryId;

	private int productQty;
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
	public long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
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
	public ProductEntity(long productId, String productName, double productCost, long supplierId, long categoryId,
			int productQty, int dileveryCharge) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.productQty = productQty;
		this.dileveryCharge = dileveryCharge;
	}
	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", productName=" + productName + ", productCost=" + productCost
				+ ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", productQty=" + productQty
				+ ", dileveryCharge=" + dileveryCharge + "]";
	}
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
