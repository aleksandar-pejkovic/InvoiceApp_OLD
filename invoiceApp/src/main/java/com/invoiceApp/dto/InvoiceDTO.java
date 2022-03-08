package com.invoiceApp.dto;

import java.util.Date;
import java.util.List;

import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Item;

public class InvoiceDTO {

	private String name;
	private Date date;
	private Customer customer;
	private List<Item> items;

	public InvoiceDTO() {
		super();
	}

	public InvoiceDTO(String name, Date date, Customer customer, List<Item> items) {
		super();
		this.name = name;
		this.date = date;
		this.customer = customer;
		this.items = items;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
