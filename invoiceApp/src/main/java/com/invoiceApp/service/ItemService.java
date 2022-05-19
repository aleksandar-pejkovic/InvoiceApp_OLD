package com.invoiceApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Invoice;
import com.invoiceApp.entity.Item;
import com.invoiceApp.entity.Product;
import com.invoiceApp.repository.ItemRepository;
import com.invoiceApp.request.ItemRequest;
import com.invoiceApp.response.ItemResponse;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	ProductService productService;

	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	public List<Item> findItems(String invoiceName) {
		try {
			Invoice invoice = invoiceService.findByName(invoiceName);
			return itemRepository.findByInvoice(invoice);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	public List<ItemResponse> itemToItemResponse(List<Item> items) {
		List<ItemResponse> itemsResponse = new ArrayList<ItemResponse>();
		for (Item item : items) {
			ItemResponse itemResponse = new ItemResponse();
			BeanUtils.copyProperties(item, itemResponse);
			itemResponse.setProductName(item.getProduct().getName());
			itemResponse.setProductPrice(item.getProduct().getPrice());
			itemsResponse.add(itemResponse);
		}
		return itemsResponse;
	}

	public ItemResponse itemToItemResponse(Item item) {
		ItemResponse itemResponse = new ItemResponse();
		BeanUtils.copyProperties(item, itemResponse);
		itemResponse.setProductName(item.getProduct().getName());
		itemResponse.setProductPrice(item.getProduct().getPrice());
		return itemResponse;
	}

	public Item itemRequestToItem(ItemRequest itemRequest) {
		Item item = new Item();
		BeanUtils.copyProperties(itemRequest, item);
		Product product = productService.findByName(itemRequest.getProductName());
		Invoice invoice = invoiceService.findByName(itemRequest.getInvoiceName());
		item.setProduct(product);
		item.setInvoice(invoice);
		item.setTotal();
		return item;
	}

}
