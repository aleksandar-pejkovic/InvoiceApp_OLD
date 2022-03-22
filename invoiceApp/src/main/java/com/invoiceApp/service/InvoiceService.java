package com.invoiceApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.dto.InvoiceDTO;
import com.invoiceApp.dto.ItemDTO;
import com.invoiceApp.dto.ProductDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.entity.Item;
import com.invoiceApp.entity.Product;
import com.invoiceApp.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository repository;
	@Autowired
	CustomerService customerService;

	public Invoice createInvoice(Invoice invoice) {
		if (repository.findByName(invoice.getName()) != null)
			throw new EntityExistsException();
		return repository.save(invoice);
	}

	public List<Invoice> createInvoices(List<Invoice> invoices) {
		return (List<Invoice>) repository.saveAll(invoices);
	}

	public Invoice findById(Long id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Invoice findByName(String name) {
		try {
			return repository.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Invoice> findByCustomer(Customer customer) {
		try {
			return repository.findByCustomer(customer);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Invoice> findAll() {
		return (List<Invoice>) repository.findAll();
	}

	public Invoice update(Invoice invoice, Long id) {
		try {
			Invoice existingInvoice = repository.findById(id).get();
			BeanUtils.copyProperties(invoice, existingInvoice, "id");
			return repository.save(existingInvoice);
		} catch (NoSuchElementException e) {
			e.toString();
			return null;
		}
	}

	public String deleteInvoice(Long id) {
		Invoice existingInvoice = new Invoice();
		try {
			existingInvoice = repository.findById(id).get();
			repository.delete(existingInvoice);
			return "Invoice deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Invoice does not exist.";
		}
	}

	/*
	 * This function changes list of Invoices and it's properties (items, product)
	 * to their's respective DTO objects
	 */
	public List<InvoiceDTO> changeInvoicesToInvoicesDTO(List<Invoice> invoices) {
		ModelMapper modelMapper = new ModelMapper();
		List<InvoiceDTO> invoicesDto = new ArrayList<>();
		for (Invoice invoice : invoices) {
			List<Item> items = invoice.getItems();
			List<ItemDTO> itemsDto = new ArrayList<>();
			for (Item item : items) {
				Product product = item.getProduct();
				ProductDTO productDto = new ProductDTO();
				modelMapper.map(product, productDto);
				ItemDTO itemDto = new ItemDTO();
				modelMapper.map(item, itemDto);
				itemDto.setProductDto(productDto);
				itemsDto.add(itemDto);
			}
			InvoiceDTO invoiceDto = new InvoiceDTO();
			modelMapper.map(invoice, invoiceDto);
			invoiceDto.setItems(itemsDto);
			invoicesDto.add(invoiceDto);
		}
		return invoicesDto;
	}

}
