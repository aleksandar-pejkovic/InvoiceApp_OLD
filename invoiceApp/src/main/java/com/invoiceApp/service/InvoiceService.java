package com.invoiceApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
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
	InvoiceRepository invoiceRepository;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;

	ModelMapper modelMapper = new ModelMapper();

	public Invoice createInvoice(Invoice invoice) {
		if (invoiceRepository.findByName(invoice.getName()) != null)
			throw new EntityExistsException();
		return invoiceRepository.save(invoice);
	}

	public Invoice findById(Long id) {
		try {
			return invoiceRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Invoice findByName(String name) {
		try {
			return invoiceRepository.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Invoice> findAll() {
		return (List<Invoice>) invoiceRepository.findAll();
	}

	public Invoice update(Invoice newInvoice) {
		try {
			Invoice existingInvoice = invoiceRepository.findByName(newInvoice.getName());
			newInvoice.setId(existingInvoice.getId());
			modelMapper.map(newInvoice, existingInvoice);
			return invoiceRepository.save(existingInvoice);
		} catch (NoSuchElementException e) {
			e.toString();
			return null;
		}
	}

	public String deleteInvoice(String name) {
		Invoice existingInvoice = new Invoice();
		try {
			existingInvoice = invoiceRepository.findByName(name);
			invoiceRepository.delete(existingInvoice);
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
	public List<InvoiceDTO> transformInvoicesToInvoicesDTO(List<Invoice> invoices) {
		List<InvoiceDTO> invoicesDto = new ArrayList<>();
		InvoiceDTO invoiceDto = new InvoiceDTO();
		for (Invoice invoice : invoices) {
			invoiceDto = invoiceToInvoiceDto(invoice);
			invoicesDto.add(invoiceDto);
		}
		return invoicesDto;
	}

	public InvoiceDTO invoiceToInvoiceDto(Invoice invoice) {
		/*List<Item> items = invoice.getItems();
		List<ItemDTO> itemsDto = new ArrayList<>();
		for (Item item : items) {
			Product product = item.getProduct();
			ProductDTO productDto = productService.convertToProductDto(product);
			ItemDTO itemDto = new ItemDTO();
			modelMapper.map(item, itemDto);
			itemDto.setProductDto(productDto);
			itemDto.setTotal();
			itemsDto.add(itemDto);
		}*/
		InvoiceDTO invoiceDto = new InvoiceDTO();
		modelMapper.map(invoice, invoiceDto);
		//invoiceDto.setItemsDto(itemsDto);
		Customer customer = customerService.findByName(invoice.getCustomer().getName());
		invoiceDto.setCustomerName(customer.getName());
		return invoiceDto;
	}

	public Invoice invoiceDtoToInvoice(InvoiceDTO invoiceDto) {
		//List<ItemDTO> itemsDto = invoiceDto.getItemsDto();
		List<Item> items = new ArrayList<>();
		Invoice returnValue = new Invoice();
		modelMapper.map(invoiceDto, returnValue);
		Customer customer = customerService.findByName(invoiceDto.getCustomerName());
		returnValue.setCustomer(customer);
		/*for (ItemDTO itemDto : itemsDto) {
			ProductDTO productDto = itemDto.getProductDto();
			Product product = productService.convertToProduct(productDto);
			Item item = new Item();
			modelMapper.map(itemDto, item);
			item.setProduct(product);
			item.setInvoice(returnValue);
			item.setTotal();
			items.add(item);
		}*/
		returnValue.setItems(items);
		return returnValue;
	}
}
