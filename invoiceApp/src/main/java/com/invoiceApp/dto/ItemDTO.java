package com.invoiceApp.dto;

public class ItemDTO {

	private double amount;
	private ProductDTO productDto;

	public ItemDTO() {
	}

	public ItemDTO(double amount, ProductDTO productDto) {
		this.amount = amount;
		this.productDto = productDto;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ProductDTO getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDTO productDto) {
		this.productDto = productDto;
	}

}
