package com.invoiceApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.dto.ItemDTO;
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
	
	public List<ItemDTO> transformToItemDTO(List<Item> items){
		List<ItemDTO> itemsDto = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDto = new ItemDTO();
			BeanUtils.copyProperties(item, itemDto);
			itemDto.setProductName(item.getProduct().getName());
			itemDto.setProductPrice(item.getProduct().getPrice());
			itemsDto.add(itemDto);
		}
		return itemsDto;
	}

}
