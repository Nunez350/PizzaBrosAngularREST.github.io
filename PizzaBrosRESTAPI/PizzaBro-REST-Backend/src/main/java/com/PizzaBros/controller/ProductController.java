package com.PizzaBros.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaBros.DTO.ProductDTO;
import com.PizzaBros.services.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {
	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/product")
	public List<ProductDTO> getAll(@RequestParam(name = "id", required = false) String category,@RequestParam(name = "related", required = false) boolean related, @RequestParam(name = "metadata", required = false) boolean metadata) {
		return productService.findAll(category);
	}
}
