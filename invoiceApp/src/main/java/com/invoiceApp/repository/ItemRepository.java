package com.invoiceApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoiceApp.entity.Invoice;
import com.invoiceApp.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{
	
	List<Item> findByInvoice(Invoice invoice);
	
}
