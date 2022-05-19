package com.invoiceApp.request;

public class CustomerRequest {
	
	private String name;
	private String address;
	private String zip;
	private String city;
	private String pib;
	private String mb;
	private String phone;
	private String bankAccount;
	private String email;

	public CustomerRequest() {
		super();
	}

	public CustomerRequest(String name, String address, String zip, String city, String pib, String mb, String phone,
			String bankAccount, String email) {
		super();
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.pib = pib;
		this.mb = mb;
		this.phone = phone;
		this.bankAccount = bankAccount;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getMb() {
		return mb;
	}

	public void setMb(String mb) {
		this.mb = mb;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
