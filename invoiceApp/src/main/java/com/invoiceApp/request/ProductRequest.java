package com.invoiceApp.request;

public class ProductRequest {
	
	private String barCode;
	private String name;
	private String unit;
	private double vat;
	private double price;

	public ProductRequest() {
	}

	public ProductRequest(String barCode, String name, String unit, double vat, double price) {
		this.barCode = barCode;
		this.name = name;
		this.unit = unit;
		this.vat = vat;
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
