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

import com.invoiceApp.dto.InvoiceDTO;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/create")
	public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDto) {
		Invoice invoice = invoiceService.invoiceDtoToInvoice(invoiceDto);
		Invoice storedInvoice = invoiceService.createInvoice(invoice);
		InvoiceDTO returnValue = invoiceService.invoiceToInvoiceDto(storedInvoice);
		return returnValue;
	}

	@PutMapping("/update")
	public Invoice updateInvoice(@RequestBody InvoiceDTO invoiceDto) {
		Invoice newInvoice = invoiceService.invoiceDtoToInvoice(invoiceDto);
		return invoiceService.update(newInvoice);
	}

	@DeleteMapping("/{name}")
	public String deleteInvoice(@PathVariable String name) {
		return invoiceService.deleteInvoice(name);
	}


	@GetMapping("/name/{name}")
	public InvoiceDTO findInvoiceByName(@PathVariable String name) {
		Invoice invoice = invoiceService.findByName(name);
		if(invoice != null)
			return invoiceService.invoiceToInvoiceDto(invoice);
		return null;
	}

	@GetMapping
	public List<Invoice> findAllInvoices() {
		return invoiceService.findAll();
	}

}
