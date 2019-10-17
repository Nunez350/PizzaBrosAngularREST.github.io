package com.PizzaBros.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaBros.DTO.PizzaDTO;
import com.PizzaBros.mapper.PizzaMapper;
import com.PizzaBros.model.Pizza;
import com.PizzaBros.repository.PizzaRepository;


@Service
@Transactional
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Autowired
	private PizzaMapper pizzaMapper;
	
	
	public PizzaDTO findOne(Long id) {
		Optional<Pizza> pizzaOp = pizzaRepository.findById(id);
		if (pizzaOp.isPresent()) {
			return pizzaMapper.toDto(pizzaOp.get());
		}
		return null;
	}
	
	
	public PizzaDTO save(PizzaDTO pizza) {
	Pizza entity = pizzaMapper.toEntity(pizza);
	Pizza saved = pizzaRepository.save(entity);
	return pizzaMapper.toDto(saved);
	}
	
	
	public PizzaDTO update(PizzaDTO pizza, Long id) {
		Optional<Pizza>findById = pizzaRepository.findById(id);
		if (findById.isPresent()) {
			Pizza p = findById.get();
			p.setName(pizza.getName());
			p.setDescription(pizza.getDescription());
			p.setImage(pizza.getImage());
			p.setPrice(pizza.getPrice());
			p.setInventory(pizza.getInventory());
			p.setSales(pizza.getSales());
			Pizza saved = PizzaRepository.save(p);
			return pizzaMapper.toDto(saved);
		} else {
			throw new IllegalArgumentException();
			}
		}
	
	public void delete(Long id) {
		pizzaRepository.deleteById(id);
	}
	

}
