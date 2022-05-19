package com.invoiceApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoiceApp.entity.Product;
import com.invoiceApp.response.ProductResponse;
import com.invoiceApp.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public ProductResponse createProduct(@RequestBody ProductResponse productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		Product storedProduct = productService.createProduct(product);
		ProductResponse returnValue = productService.convertToProductDto(storedProduct);
		return returnValue;
	}

	@PutMapping("/update")
	public ProductResponse updateProduct(@RequestBody ProductResponse productDto) {
		Product product = productService.convertToProduct(productDto);
		Product storedProduct = productService.updateProduct(product);
		ProductResponse returnValue = productService.convertToProductDto(storedProduct);
		return returnValue;
	}

	@DeleteMapping("delete/{name}")
	public String deleteProduct(@PathVariable String name) {
		return productService.deleteProduct(name);
	}

	@GetMapping("/listAll")
	public List<ProductResponse> findAllProducts() {
		List<Product> products = productService.findAll();
		List<ProductResponse> productsDto = new ArrayList<>();
		for (Product product : products) {
			ProductResponse productDto = productService.convertToProductDto(product);
			productsDto.add(productDto);
		}
		return productsDto;
	}

	@GetMapping("/barCode/{barCode}")
	public ProductResponse findCustomerByPib(@PathVariable String barCode) {
		try {
			Product product = productService.findByBarCode(barCode);
			return productService.convertToProductDto(product);
		} catch (NullPointerException e) {
			e.getMessage();
			return new ProductResponse();
		}
	}
	
	@GetMapping("/name/{name}")
	public ProductResponse findCustomerByName(@PathVariable String name) {
		Product product = productService.findByName(name);
		if (product != null)
			return productService.convertToProductDto(product);
		return null;
	}

}
