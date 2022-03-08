package com.invoiceApp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository repo;
	@Autowired
	CustomerService customerService;

	public Invoice newInvoice(Invoice invoice) {
		//Invoice checkInvoice = new Invoice();
		//checkInvoice = repo.findByName(invoice.getName());
		//if (checkInvoice == null) {
			//if (invoice.getItems() != null)
				//invoice.setTotal();
			//Customer customer = new Customer();
			//customer = customerService.findByName(invoice.getCustomer().getName());
			//customer.getInvoices().add(invoice);
			//customer.setTotalDebt();
			//customerService.update(customer, customer.getId());
			return repo.save(invoice);
		//} else
		//	return null;
	}

	public List<Invoice> newInvoices(List<Invoice> invoices) {
		return (List<Invoice>) repo.saveAll(invoices);
	}

	public Invoice findById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Invoice findByName(String name) {
		try {
			return repo.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Invoice> findByCustomer(Customer customer) {
		try {
			return repo.findByCustomer(customer);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Invoice> findAll() {
		return (List<Invoice>) repo.findAll();
	}

	public Invoice update(Invoice invoice, Long id) {
		try {
			Invoice existingInvoice = repo.findById(id).get();
			BeanUtils.copyProperties(invoice, existingInvoice, "id");
			return repo.save(existingInvoice);
		} catch (NoSuchElementException e) {
			e.toString();
			return null;
		}
	}

	public String deleteInvoice(Long id) {
		Invoice existingInvoice = new Invoice();
		try {
			existingInvoice = repo.findById(id).get();
			repo.delete(existingInvoice);
			return "Invoice deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Invoice does not exist.";
		}
	}

}
