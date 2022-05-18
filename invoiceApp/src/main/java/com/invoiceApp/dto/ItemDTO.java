package com.invoiceApp.dto;

public class ItemDTO {

	private double amount;
	private String productName;
	private double productPrice;
	private double total;

	public ItemDTO() {
	}

	public ItemDTO(double amount, String productName, double productPrice, double total) {
		super();
		this.amount = amount;
		this.productName = productName;
		this.productPrice = productPrice;
		this.total = total;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
