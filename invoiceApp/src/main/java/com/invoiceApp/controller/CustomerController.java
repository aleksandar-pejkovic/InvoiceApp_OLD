package com.invoiceApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoiceApp.dto.CustomerDTO;
import com.invoiceApp.dto.InvoiceDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.service.CustomerService;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/create")
	public Customer createCustomer(@RequestBody CustomerDTO customerDto) {
		ModelMapper modelMapper = new ModelMapper();
		Customer customer = new Customer();
		modelMapper.map(customerDto, customer);
		return customerService.createCustomer(customer);
	}

	@PostMapping("/createAll")
	public List<Customer> createCustomers(@RequestBody List<Customer> customers) {
		return customerService.createCustomers(customers);
	}

	@PutMapping("/update/{oldName}")
	public Customer updateCustomer(@RequestBody CustomerDTO newCustomerDto, @PathVariable String oldName) {
		ModelMapper modelMapper = new ModelMapper();
		Customer newCustomer = new Customer();
		modelMapper.map(newCustomerDto, newCustomer);
		return customerService.updateCustomer(newCustomer, oldName);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}

	@GetMapping("/id/{id}")
	public CustomerDTO findCustomerById(@PathVariable Long id) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			CustomerDTO customerDto = new CustomerDTO();
			Customer customer = customerService.findById(id);
			modelMapper.map(customer, customerDto);
			return customerDto;
		} catch (NullPointerException e) {
			e.getMessage();
			return new CustomerDTO();
		}
	}

	@GetMapping("/name/{name}")
	public CustomerDTO findCustomerByName(@PathVariable String name) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			CustomerDTO customerDto = new CustomerDTO();
			Customer customer = customerService.findByName(name);
			modelMapper.map(customer, customerDto);
			return customerDto;
		} catch (NullPointerException e) {
			e.getMessage();
			return new CustomerDTO();
		}
	}

	@GetMapping("/pib/{pib}")
	public CustomerDTO findCustomerByPib(@PathVariable String pib) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			CustomerDTO customerDto = new CustomerDTO();
			Customer customer = customerService.findByName(pib);
			modelMapper.map(customer, customerDto);
			return customerDto;
		} catch (NullPointerException e) {
			e.getMessage();
			return new CustomerDTO();
		}
	}

	@GetMapping("/name/{name}/invoices")
	public List<InvoiceDTO> getCustomerInvoices(@PathVariable String name) {
		List<Invoice> invoices = customerService.findByName(name).getInvoices();
		return invoiceService.changeInvoicesToInvoicesDTO(invoices);
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

	@GetMapping("/listAll")
	public List<CustomerDTO> findAllCustomers() {
		ModelMapper modelMapper = new ModelMapper();
		List<Customer> customers = customerService.findAll();
		List<CustomerDTO> customersDto = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDTO customerDto = new CustomerDTO();
			modelMapper.map(customer, customerDto);
			customersDto.add(customerDto);
		}
		return customersDto;
	}

}
