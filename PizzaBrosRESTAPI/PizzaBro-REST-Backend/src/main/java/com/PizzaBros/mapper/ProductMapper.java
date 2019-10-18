package com.PizzaBros.mapper;

import org.springframework.stereotype.Component;

import com.PizzaBros.DTO.ProductDTO;
import com.PizzaBros.model.Product;
import com.PizzaBros.repository.ProductRepository;

@Component
public class ProductMapper {

	
	public Product toEntity(ProductDTO dto) {
		Product pizza = new Product();		
		pizza.setName(dto.getName());
		pizza.setDescription(dto.getDescription());
		pizza.setImage(dto.getImage());
		pizza.setPrice(dto.getPrice());
		pizza.setInventory(dto.getInventory());
		pizza.setSales(dto.getSales());
		return pizza;
	}
	
	public ProductDTO toDto(Product entity) {
		ProductDTO product = new ProductDTO();
		product.setProductId(entity.getId());
		product.setName(entity.getName());
		product.setDescription(entity.getDescription());
		product.setImage(entity.getImage());
		product.setPrice(entity.getPrice());
		product.setInventory(entity.getInventory());
		product.setSales(entity.getSales());
		return product;
	}
	
	
}
