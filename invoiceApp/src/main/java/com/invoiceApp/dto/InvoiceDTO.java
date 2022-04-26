package com.invoiceApp.dto;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {

	private String name;
	private Date date;
	private CustomerDTO customerDto;
	private List<ItemDTO> itemsDto;

	public InvoiceDTO() {
	}

	public InvoiceDTO(String name, Date date, CustomerDTO customerDto, List<ItemDTO> itemsDto) {
		this.name = name;
		this.date = date;
		this.customerDto = customerDto;
		this.itemsDto = itemsDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public List<ItemDTO> getItemsDto() {
		return itemsDto;
	}

	public void setItemsDto(List<ItemDTO> itemsDto) {
		this.itemsDto = itemsDto;
	}

}
