package com.PizzaBros.mapper;

import org.springframework.stereotype.Component;

import com.PizzaBros.DTO.PizzaDTO;
import com.PizzaBros.model.Pizza;
import com.PizzaBros.repository.PizzaRepository;

@Component
public class PizzaMapper {

	
	public Pizza toEntity(PizzaDTO dto) {
		Pizza pizza = new Pizza();		
		pizza.setName(dto.getName());
		pizza.setDescription(dto.getDescription());
		pizza.setImage(dto.getImage());
		pizza.setPrice(dto.getPrice());
		pizza.setInventory(dto.getInventory());
		pizza.setSales(dto.getSales());
		return pizza;
	}
	
	public PizzaDTO toDto(Pizza entity) {
		PizzaDTO pizza = new PizzaDTO();
		pizza.setPizzaId(entity.getId());
		pizza.setName(entity.getName());
		pizza.setDescription(entity.getDescription());
		pizza.setImage(entity.getImage());
		pizza.setPrice(entity.getPrice());
		pizza.setInventory(entity.getInventory());
		pizza.setSales(entity.getSales());
		return pizza;
	}
	
	
}
