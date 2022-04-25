package com.invoiceApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoiceApp.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	Product findByName(String name);
	Product findByBarCode(String barCode);

}
