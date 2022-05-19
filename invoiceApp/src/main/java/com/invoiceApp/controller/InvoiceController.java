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

import com.invoiceApp.entity.Invoice;
import com.invoiceApp.request.InvoiceRequest;
import com.invoiceApp.response.InvoiceResponse;
import com.invoiceApp.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/create")
	public InvoiceResponse createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		Invoice invoice = invoiceService.invoiceRequestToInvoice(invoiceRequest);
		Invoice storedInvoice = invoiceService.createInvoice(invoice);
		InvoiceResponse returnValue = invoiceService.invoiceToInvoiceResponse(storedInvoice);
		return returnValue;
	}

	@PutMapping("/update")
	public Invoice updateInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		Invoice newInvoice = invoiceService.invoiceRequestToInvoice(invoiceRequest);
		return invoiceService.update(newInvoice);
	}

	@DeleteMapping("/{name}")
	public String deleteInvoice(@PathVariable String name) {
		return invoiceService.deleteInvoice(name);
	}


	@GetMapping("/name/{name}")
	public InvoiceResponse findInvoiceByName(@PathVariable String name) {
		Invoice invoice = invoiceService.findByName(name);
		if(invoice != null)
			return invoiceService.invoiceToInvoiceResponse(invoice);
		return null;
	}

	@GetMapping("/listAll")
	public List<InvoiceResponse> findAllInvoices() {
		List<Invoice> invoices = invoiceService.findAll();
		return invoiceService.transformInvoicesToInvoicesDTO(invoices);
	}

}
