package com.PizzaBros.PizzaBroRESTBackend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/api/products/{id}")
	public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
		ProductDTO product = productService.findOne(id);
		return ResponseEntity.ok(product);
	}
	
//	@GetMapping("/api/products/{category}")
//	public ResponseEntity<ProductDTO> get(@PathVariable String category) {
//		ProductDTO product = productService.findOne(category);
//		return ResponseEntity.ok(product);
//	}
//	
	
	@PostMapping("/api/products")
	public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO product) throws URISyntaxException {
		ProductDTO result = productService.save(product);
		return ResponseEntity.created(new URI("/api/products/" + result.getProductId())).body(result);
	}
	
	@PutMapping("/api/products/{id}")
	public ResponseEntity<ProductDTO> updateJob(@PathVariable Long id, @RequestBody @Valid ProductDTO product) {
		ProductDTO result = productService.update(product, id);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("api/products/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.ok().build();
	}
}
