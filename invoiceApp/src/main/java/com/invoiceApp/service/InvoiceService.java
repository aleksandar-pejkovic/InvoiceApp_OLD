package com.invoiceApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.repository.InvoiceRepository;
import com.invoiceApp.request.InvoiceRequest;
import com.invoiceApp.response.InvoiceResponse;

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
	public List<InvoiceResponse> transformInvoicesToInvoicesDTO(List<Invoice> invoices) {
		List<InvoiceResponse> invoicesDto = new ArrayList<>();
		InvoiceResponse invoiceDto = new InvoiceResponse();
		for (Invoice invoice : invoices) {
			invoiceDto = invoiceToInvoiceResponse(invoice);
			invoicesDto.add(invoiceDto);
		}
		return invoicesDto;
	}

	public InvoiceResponse invoiceToInvoiceResponse(Invoice invoice) {
		/*
		 * List<Item> items = invoice.getItems(); List<ItemDTO> itemsDto = new
		 * ArrayList<>(); for (Item item : items) { Product product = item.getProduct();
		 * ProductDTO productDto = productService.convertToProductDto(product); ItemDTO
		 * itemDto = new ItemDTO(); modelMapper.map(item, itemDto);
		 * itemDto.setProductDto(productDto); itemDto.setTotal(); itemsDto.add(itemDto);
		 * }
		 */
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		modelMapper.map(invoice, invoiceResponse);
		// invoiceDto.setItemsDto(itemsDto);
		Customer customer = customerService.findByName(invoice.getCustomer().getName());
		invoiceResponse.setCustomerName(customer.getName());
		return invoiceResponse;
	}

	public Invoice invoiceRequestToInvoice(InvoiceRequest invoiceRequest) {
		// List<ItemDTO> itemsDto = invoiceDto.getItemsDto();

		Invoice invoice = new Invoice();
		modelMapper.map(invoiceRequest, invoice);
		Customer customer = customerService.findByName(invoiceRequest.getCustomerName());
		invoice.setCustomer(customer);
		/*
		 * List<Item> items = new ArrayList<>(); for (ItemDTO itemDto : itemsDto) {
		 * ProductDTO productDto = itemDto.getProductDto(); Product product =
		 * productService.convertToProduct(productDto); Item item = new Item();
		 * modelMapper.map(itemDto, item); item.setProduct(product);
		 * item.setInvoice(returnValue); item.setTotal(); items.add(item); }
		 * invoice.setItems(items);
		 */
		return invoice;
	}
}
