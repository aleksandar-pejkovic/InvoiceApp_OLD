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

import com.invoiceApp.dto.ProductDTO;
import com.invoiceApp.entity.Product;
import com.invoiceApp.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public ProductDTO createProduct(@RequestBody ProductDTO productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		Product storedProduct = productService.createProduct(product);
		ProductDTO returnValue = productService.convertToProductDto(storedProduct);
		return returnValue;
	}

	@PutMapping("/update")
	public ProductDTO updateProduct(@RequestBody ProductDTO productDto) {
		Product product = productService.convertToProduct(productDto);
		Product storedProduct = productService.updateProduct(product);
		ProductDTO returnValue = productService.convertToProductDto(storedProduct);
		return returnValue;
	}

	@DeleteMapping("delete/{name}")
	public String deleteProduct(@PathVariable String name) {
		return productService.deleteProduct(name);
	}

	@GetMapping("/listAll")
	public List<ProductDTO> findAllProducts() {
		List<Product> products = productService.findAll();
		List<ProductDTO> productsDto = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDto = productService.convertToProductDto(product);
			productsDto.add(productDto);
		}
		return productsDto;
	}

	@GetMapping("/barCode/{barCode}")
	public ProductDTO findCustomerByPib(@PathVariable String barCode) {
		try {
			Product product = productService.findByBarCode(barCode);
			return productService.convertToProductDto(product);
		} catch (NullPointerException e) {
			e.getMessage();
			return new ProductDTO();
		}
	}
	
	@GetMapping("/name/{name}")
	public ProductDTO findCustomerByName(@PathVariable String name) {
		Product product = productService.findByName(name);
		if (product != null)
			return productService.convertToProductDto(product);
		return null;
	}

}
