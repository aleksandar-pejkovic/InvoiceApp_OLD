package com.invoiceApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoiceApp.dto.CustomerDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.service.CustomerService;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService service;

	@Autowired
	InvoiceService invoiceService;

	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.newCustomer(customer);
	}

	@PostMapping("/all")
	public List<Customer> createCustomers(@RequestBody List<Customer> customers) {
		return service.newCustomers(customers);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody CustomerDTO newCustomerDto, @RequestBody CustomerDTO oldCustomerDto) {
		return service.update(newCustomerDto, oldCustomerDto);
	}

	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		return service.deleteCustomer(id);
	}

	@GetMapping("/id/{id}")
	public Customer findCustomerById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/name/{name}")
	public Customer findCustomerByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@GetMapping("/pib/{pib}")
	public Customer findCustomerByPib(@PathVariable String pib) {
		return service.findByPib(pib);
	}

	@GetMapping("/name/{name}/invoices")
	public List<Invoice> getCustomerInvoices(@PathVariable String name) {
		return service.findByName(name).getInvoices();
	}

	@GetMapping("/name/{name}/invoices/{invoiceId}")
	public Invoice getUserInvoice(@PathVariable String name, @PathVariable Long invoiceId) {

		Invoice returnValue = invoiceService.findById(invoiceId);
		
		Link customerLink = WebMvcLinkBuilder.linkTo(Customer.class)
				.slash(name)
				.withRel("customer");
		Link customerInvoicesLink = WebMvcLinkBuilder.linkTo(Customer.class)
				.slash(name)
				.slash("invoices")
				.withRel("invoices");
		Link selfLink = WebMvcLinkBuilder.linkTo(Customer.class)
				.slash(name)
				.slash("invoices")
				.slash(invoiceId)
				.withSelfRel();
		
		returnValue.add(customerLink);
		returnValue.add(customerInvoicesLink);
		returnValue.add(selfLink);
		
		return returnValue;
		
	}

	@GetMapping
	public List<Customer> findAllCustomers() {
		return service.findAll();
	}

}
