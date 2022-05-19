package com.invoiceApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.response.CustomerResponse;
import com.invoiceApp.response.InvoiceResponse;
import com.invoiceApp.service.CustomerService;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	InvoiceService invoiceService;

	// http://localhost:8080/customers

	/*
	 * JSON example for creating new company: { "name" : "CompanyX", "pib" :
	 * "111222333", "bankAccount" : "170-1122334455-17" }
	 */

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public CustomerResponse createCustomer(@RequestBody CustomerResponse customerDto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		Customer storedCustomer = customerService.createCustomer(customer);
		CustomerResponse returnValue = customerService.customerToCustomerDto(storedCustomer);
		return returnValue;
	}

	@PutMapping("/update")
	public CustomerResponse updateCustomer(@RequestBody CustomerResponse newCustomerDto) {
		Customer newCustomer = customerService.customerDtoToCustomer(newCustomerDto);
		Customer storedCustomer = customerService.updateCustomer(newCustomer);
		CustomerResponse returnValue = customerService.customerToCustomerDto(storedCustomer);
		return returnValue;
	}

	@DeleteMapping("/delete/{name}")
	public String deleteCustomer(@PathVariable String name) {
		return customerService.deleteCustomer(name);
	}

	@GetMapping("/name/{name}")
	public CustomerResponse findCustomerByName(@PathVariable String name) {
		Customer customer = customerService.findByName(name);
		if (customer != null)
			return customerService.customerToCustomerDto(customer);
		return null;
	}

	@GetMapping("/pib/{pib}")
	public CustomerResponse findCustomerByPib(@PathVariable String pib) {
		try {
			Customer customer = customerService.findByPib(pib);
			return customerService.customerToCustomerDto(customer);
		} catch (NullPointerException e) {
			e.getMessage();
			return new CustomerResponse();
		}
	}

	@GetMapping("/name/{name}/invoices")
	public List<InvoiceResponse> getCustomerInvoices(@PathVariable String name) {
		List<Invoice> invoices = customerService.findByName(name).getInvoices();
		return invoiceService.transformInvoicesToInvoicesDTO(invoices);
	}

	/*
	 * @GetMapping("/name/{name}/invoices/{invoiceId}") public Invoice
	 * getUserInvoice(@PathVariable String name, @PathVariable Long invoiceId) {
	 * 
	 * Invoice returnValue = invoiceService.findById(invoiceId);
	 * 
	 * Link customerLink =
	 * WebMvcLinkBuilder.linkTo(Customer.class).slash(name).withRel("customer");
	 * Link customerInvoicesLink =
	 * WebMvcLinkBuilder.linkTo(Customer.class).slash(name).slash("invoices")
	 * .withRel("invoices"); Link selfLink =
	 * WebMvcLinkBuilder.linkTo(Customer.class).slash(name).slash("invoices").slash(
	 * invoiceId) .withSelfRel();
	 * 
	 * returnValue.add(customerLink); returnValue.add(customerInvoicesLink);
	 * returnValue.add(selfLink);
	 * 
	 * return returnValue; }
	 */

	@GetMapping(value = "/listAll", produces = "application/json")
	public List<CustomerResponse> findAllCustomers() {
		List<Customer> customers = customerService.findAll();
		List<CustomerResponse> customersDto = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerResponse customerDto = customerService.customerToCustomerDto(customer);
			customersDto.add(customerDto);
		}
		return customersDto;
	}

}
