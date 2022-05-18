package com.invoiceApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Invoice;
import com.invoiceApp.entity.Item;
import com.invoiceApp.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	InvoiceService invoiceService;
	
	public List<Item> findItems(String invoiceName){
		try {
			Invoice invoice = invoiceService.findByName(invoiceName);
			return itemRepository.findByInvoice(invoice);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

}
