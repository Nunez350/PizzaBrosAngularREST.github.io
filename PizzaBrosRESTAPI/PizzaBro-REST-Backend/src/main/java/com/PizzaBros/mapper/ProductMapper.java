package com.PizzaBros.mapper;

import org.springframework.stereotype.Component;

import com.PizzaBros.DTO.ProductDTO;
import com.PizzaBros.model.Product;
import com.PizzaBros.repository.ProductRepository;

@Component
public class ProductMapper {

	
	public Product toEntity(ProductDTO dto) {
		Product product = new Product();		
		product.setCategory(dto.getCategory());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setImage(dto.getImage());
		product.setPrice(dto.getPrice());
		product.setInventory(dto.getInventory());
		product.setSales(dto.getSales());
		return product;
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
