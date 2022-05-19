package com.invoiceApp.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoiceApp.entity.Product;
import com.invoiceApp.repository.ProductRepository;
import com.invoiceApp.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	ModelMapper modelMapper = new ModelMapper();

	public Product createProduct(Product product) {
		if (productRepository.findByName(product.getName()) != null)
			throw new EntityExistsException();
		return productRepository.save(product);
	}

	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	public Product findByName(String name) {
		try {
			return productRepository.findByName(name);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Product findById(Long id) {
		try {
			return productRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Product findByBarCode(String barCode) {
		try {
			return productRepository.findByBarCode(barCode);
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
			return null;
		}
	}

	public Product updateProduct(Product product) {
		try {
			Product existingProduct = productRepository.findByBarCode(product.getBarCode());
			product.setId(existingProduct.getId());
			product.setItems(existingProduct.getItems());
			return productRepository.save(product);
		} catch (NoSuchElementException e) {
			e.toString();
			return new Product();
		}
	}

	public String deleteProduct(String name) {
		try {
			Product product = productRepository.findByName(name);
			productRepository.delete(product);
			return "Customer deleted";
		} catch (NoSuchElementException e) {
			e.toString();
			return "Customer does not exist.";
		}
	}

	public ProductResponse convertToProductDto(Product product) {
		ProductResponse productDto = new ProductResponse();
		modelMapper.map(product, productDto);
		return productDto;
	}

	public Product convertToProduct(ProductResponse productDto) {
		Product product = findByName(productDto.getName());
		return product;
	}

}
