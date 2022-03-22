package com.invoiceApp.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Customer;
import com.invoiceApp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	public Customer createCustomer(Customer customer) {

		if (repository.findByName(customer.getName()) != null)
			throw new EntityExistsException();
		
		return repository.save(customer);
	}

	public List<Customer> createCustomers(List<Customer> customers) {
		return (List<Customer>) repository.saveAll(customers);
	}

	public Customer findById(Long id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByName(String name) {
		try {
			return repository.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByPib(String pib) {
		try {
			return repository.findByPib(pib);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}

	public Customer updateCustomer(Customer newCustomer, String oldName) {
		try {
			Customer existingCustomer = repository.findByName(oldName);
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(newCustomer, existingCustomer);
			return repository.save(existingCustomer);
		} catch (NoSuchElementException e) {
			e.toString();
			return null;
		}
	}

	public String deleteCustomer(Long id) {
		try {
			Customer existingCustomer = new Customer();
			existingCustomer = repository.findById(id).get();
			repository.delete(existingCustomer);
			return "Customer deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Customer does not exist.";
		}
	}

}
