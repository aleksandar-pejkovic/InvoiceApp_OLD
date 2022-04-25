package com.invoiceApp.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.invoiceApp.entity.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
	
	Invoice findByName(String name);
	Invoice findByDate(Date date);

}
