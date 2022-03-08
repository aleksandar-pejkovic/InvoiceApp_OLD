package com.invoiceApp.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.dto.CustomerDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.entity.Invoice;
import com.invoiceApp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	public Customer newCustomer(Customer customer) {
		
		if (repo.findByName(customer.getName()) == null) {
			for (int i = 0; i < customer.getInvoices().size(); i++) {
				Invoice invoice = customer.getInvoices().get(i);
				invoice.setCustomer(customer);
				customer.getInvoices().set(i, invoice);
			}

			return repo.save(customer);
		} else
			return null;
	}

	public List<Customer> newCustomers(List<Customer> customers) {
		return (List<Customer>) repo.saveAll(customers);
	}

	public Customer findById(Long id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByName(String name) {
		try {
			return repo.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByPib(String pib) {
		try {
			return repo.findByPib(pib);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Customer> findAll() {
		return (List<Customer>) repo.findAll();
	}

	public Customer update(CustomerDTO newCustomerDto, CustomerDTO oldCustomerDto) {
		try {
			Customer existingCustomer = repo.findByName(oldCustomerDto.getName());
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(newCustomerDto, existingCustomer);
			return repo.save(existingCustomer);
		} catch (NoSuchElementException e) {
			e.toString();
			return null;
		}
	}

	public String deleteCustomer(Long id) {
		Customer existingCustomer = new Customer();
		try {
			existingCustomer = repo.findById(id).get();
			repo.delete(existingCustomer);
			return "Customer deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Customer does not exist.";
		}
	}

}
