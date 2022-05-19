package com.invoiceApp.response;

public class ItemResponse {

	private String productName;
	private double amount;
	private double productPrice;
	private double total;

	public ItemResponse() {
	}

	public ItemResponse(String productName, double amount, double productPrice, double total) {
		super();
		this.productName = productName;
		this.amount = amount;		
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
