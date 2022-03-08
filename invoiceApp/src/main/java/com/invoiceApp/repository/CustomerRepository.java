package com.invoiceApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoiceApp.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByName(String name);
	Customer findByPib(String pib);

}
