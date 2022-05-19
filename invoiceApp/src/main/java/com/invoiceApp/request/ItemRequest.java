package com.invoiceApp.request;

public class ItemRequest {
	
	private String productName;
	private double amount;
	private String invoiceName;

	public ItemRequest() {
	}

	public ItemRequest(String productName, double amount, String invoiceName) {
		super();
		this.productName = productName;
		this.amount = amount;		
		this.invoiceName = invoiceName;
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

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	
	

}
