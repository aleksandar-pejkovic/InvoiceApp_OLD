package com.invoiceApp.controller;

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

import com.invoiceApp.dto.InvoiceDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/create")
	public Invoice createInvoice(@RequestBody InvoiceDTO invoiceDto) {
		Invoice invoice = new Invoice();
		BeanUtils.copyProperties(invoiceDto, invoice);
		return invoiceService.createInvoice(invoice);
	}

	@PutMapping("/update/{name}")
	public Invoice updateInvoice(@RequestBody InvoiceDTO invoiceDto, @PathVariable String oldName) {
		Invoice newInvoice = invoiceService.invoiceDtoToInvoice(invoiceDto);
		return invoiceService.update(newInvoice, oldName);
	}

	@DeleteMapping("/{name}")
	public String deleteInvoice(@PathVariable String name) {
		return invoiceService.deleteInvoice(name);
	}


	@GetMapping("/name/{name}")
	public Invoice findInvoiceByName(@PathVariable String name) {
		return invoiceService.findByName(name);
	}

	@GetMapping("/byCustomer")
	public List<Invoice> findInvoiceByCustomer(@RequestBody Customer customer) {
		return invoiceService.findByCustomer(customer);
	}

	@GetMapping
	public List<Invoice> findAllInvoices() {
		return invoiceService.findAll();
	}

}
