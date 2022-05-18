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

	// http://localhost:8080/customers

	/*
	 * JSON example for creating new company: { "name" : "CompanyX", "pib" :
	 * "111222333", "bankAccount" : "170-1122334455-17" }
	 */

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto, customer);
		Customer storedCustomer = customerService.createCustomer(customer);
		CustomerDTO returnValue = customerService.customerToCustomerDto(storedCustomer);
		return returnValue;
	}

	@PutMapping("/update")
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO newCustomerDto) {
		Customer newCustomer = customerService.customerDtoToCustomer(newCustomerDto);
		Customer storedCustomer = customerService.updateCustomer(newCustomer);
		CustomerDTO returnValue = customerService.customerToCustomerDto(storedCustomer);
		return returnValue;
	}

	@DeleteMapping("/delete/{name}")
	public String deleteCustomer(@PathVariable String name) {
		return customerService.deleteCustomer(name);
	}

	@GetMapping("/name/{name}")
	public CustomerDTO findCustomerByName(@PathVariable String name) {
		Customer customer = customerService.findByName(name);
		if (customer != null)
			return customerService.customerToCustomerDto(customer);
		return null;
	}

	@GetMapping("/pib/{pib}")
	public CustomerDTO findCustomerByPib(@PathVariable String pib) {
		try {
			Customer customer = customerService.findByPib(pib);
			return customerService.customerToCustomerDto(customer);
		} catch (NullPointerException e) {
			e.getMessage();
			return new CustomerDTO();
		}
	}

	@GetMapping("/name/{name}/invoices")
	public List<InvoiceDTO> getCustomerInvoices(@PathVariable String name) {
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
	public List<CustomerDTO> findAllCustomers() {
		List<Customer> customers = customerService.findAll();
		List<CustomerDTO> customersDto = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDTO customerDto = customerService.customerToCustomerDto(customer);
			customersDto.add(customerDto);
		}
		return customersDto;
	}

}
