package com.invoiceApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoiceApp.entity.Item;
import com.invoiceApp.request.ItemRequest;
import com.invoiceApp.response.ItemResponse;
import com.invoiceApp.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;

	@PostMapping("/create")
	public ItemResponse createItem(@RequestBody ItemRequest itemRequest) {
		Item item = itemService.itemRequestToItem(itemRequest);
		Item storedItem = itemService.createItem(item);
		ItemResponse itemResponse = itemService.itemToItemResponse(storedItem);
		return itemResponse;
	}
	
	@GetMapping("/list/{invoiceName}")
	public List<ItemResponse> findItems(@PathVariable String invoiceName){
		List<Item> items = itemService.findItems(invoiceName);
		return itemService.itemToItemResponse(items);
	}
	
}
