package com.invoiceApp.dto;

public class CustomerDTO {

	private String name;
	private String pib;
	private String bankAccount;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String name, String pib, String bankAccount) {
		super();
		this.name = name;
		this.pib = pib;
		this.bankAccount = bankAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

}
