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
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService service;

	@PostMapping
	public Invoice createInvoice(@RequestBody Invoice invoice) {
		return service.newInvoice(invoice);
	}

	@PostMapping("/all")
	public List<Invoice> createInvoices(@RequestBody List<Invoice> invoices) {
		return service.newInvoices(invoices);
	}

	@PutMapping("/{id}")
	public Invoice updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
		return service.update(invoice, id);
	}

	@DeleteMapping("/{id}")
	public String deleteInvoice(@PathVariable Long id) {
		return service.deleteInvoice(id);
	}

	@GetMapping("/id/{id}")
	public Invoice findInvoiceById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/name/{name}")
	public Invoice findInvoiceByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@GetMapping("/byCustomer")
	public List<Invoice> findInvoiceByCustomer(@RequestBody Customer customer) {
		return service.findByCustomer(customer);
	}

	@GetMapping
	public List<Invoice> findAllInvoices() {
		return service.findAll();
	}

}
