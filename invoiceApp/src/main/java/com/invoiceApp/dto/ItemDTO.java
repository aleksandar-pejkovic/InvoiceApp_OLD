package com.invoiceApp.dto;

public class ItemDTO {

	private double amount;
	private ProductDTO productDto;
	private double total;

	public ItemDTO() {
	}

	public ItemDTO(double amount, ProductDTO productDto) {
		this.amount = amount;
		this.productDto = productDto;
		this.setTotal();
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

	public double getTotal() {
		return total;
	}
	
	public void setTotal() {
		this.total = amount * this.productDto.getPrice();
	}

}
