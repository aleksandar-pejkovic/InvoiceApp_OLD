package com.invoiceApp.controller;

import java.util.List;

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
import com.invoiceApp.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		return service.newCustomer(customer);
	}
	
	@PostMapping("/all")
	public List<Customer> createCustomers(@RequestBody List<Customer> customers) {
		return service.newCustomers(customers);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		return service.update(customer, id);
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

	@GetMapping
	public List<Customer> findAllCustomers() {
		return service.findAll();
	}

}
