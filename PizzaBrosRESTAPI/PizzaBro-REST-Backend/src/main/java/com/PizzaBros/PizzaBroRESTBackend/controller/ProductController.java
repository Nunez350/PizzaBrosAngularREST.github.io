package com.PizzaBros.PizzaBroRESTBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductDTO;
import com.PizzaBros.PizzaBroRESTBackend.services.ProductService;


@RestController
//@RequestMapping("/api")
public class ProductController {
	private final ProductService productService;
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/api/products")
	public List<ProductDTO> getAll(@RequestParam(name = "category", required = false) String category,
	@RequestParam(name = "related", required = false) boolean related, 
	@RequestParam(name = "metadata", required = false) boolean metadata) {
		return productService.findAll(category);
	}
}
