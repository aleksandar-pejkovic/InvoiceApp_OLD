package com.invoiceApp.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.dto.CustomerDTO;
import com.invoiceApp.entity.Customer;
import com.invoiceApp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	ModelMapper modelMapper = new ModelMapper();

	public Customer createCustomer(Customer customer) {
		if (customerRepository.findByName(customer.getName()) != null)
			throw new EntityExistsException();
		return customerRepository.save(customer);
	}

	public List<Customer> createCustomers(List<Customer> customers) {
		return (List<Customer>) customerRepository.saveAll(customers);
	}

	public Customer findById(Long id) {
		try {
			return customerRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByName(String name) {
		try {
			return customerRepository.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Customer findByPib(String pib) {
		try {
			return customerRepository.findByPib(pib);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}

	public Customer updateCustomer(Customer newCustomer, String oldName) {
		try {
			Customer existingCustomer = customerRepository.findByName(oldName);
			newCustomer.setId(existingCustomer.getId());
			newCustomer.setInvoices(existingCustomer.getInvoices());
			modelMapper.map(newCustomer, existingCustomer);
			return customerRepository.save(existingCustomer);
		} catch (NoSuchElementException e) {
			e.toString();
			return new Customer();
		}
	}

	public String deleteCustomer(String name) {
		try {
			Customer existingCustomer = customerRepository.findByName(name);
			customerRepository.delete(existingCustomer);
			return "Customer deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Customer does not exist.";
		}
	}

	public CustomerDTO customerToCustomerDto(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();
		modelMapper.map(customer, customerDto);
		return customerDto;
	}

	public Customer customerDtoToCustomer(CustomerDTO customerDto) {
		Customer customer = findByName(customerDto.getName());
		modelMapper.map(customerDto, customer);
		return customer;
	}

	public Customer customerDtoToCustomer(CustomerDTO customerDto, String oldName) {
		Customer customer = findByName(oldName);
		modelMapper.map(customerDto, customer);
		return customer;
	}

}
