package com.PizzaBros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaBros.DTO.PizzaDTO;
import com.PizzaBros.mapper.PizzaMapper;
import com.PizzaBros.model.Product;
import com.PizzaBros.repository.PizzaRepository;



@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository pizzaRepository;
	
	@Autowired
	private ProductMapper pizzaMapper;
	
	
	public List<ProductDTO> findAll(String category) {
		List<Product> findAll = null;
		
		if (category != null && !category.isEmpty()) {
			findAll = pizzaRepository.findAllByCategory(category);
		} else {
			findAll = pizzaRepository.findAll();
		}
		return findAll.stream().map(m -> pizzaMapper.toDto(m)).collect(Collectors.toList());
	}
	
	
	public ProductDTO findOne(Long id) {
		Optional<Product> pizzaOp = pizzaRepository.findById(id);
		if (pizzaOp.isPresent()) {
			return pizzaMapper.toDto(pizzaOp.get());
		}
		return null;
	}
	
	
	public ProductDTO save(ProductDTO pizza) {
	Pizza entity = pizzaMapper.toEntity(pizza);
	Pizza saved = pizzaRepository.save(entity);
	return pizzaMapper.toDto(saved);
	}
	
	
	public ProductDTO update(ProductDTO pizza, Long id) {
		Optional<Product>findById = pizzaRepository.findById(id);
		if (findById.isPresent()) {
			Product p = findById.get();
			p.setName(pizza.getName());
			p.setDescription(pizza.getDescription());
			p.setImage(pizza.getImage());
			p.setPrice(pizza.getPrice());
			p.setInventory(pizza.getInventory());
			p.setSales(pizza.getSales());
			
			ProductDTO pizzaDTO = new ProductDTO();
			//Pizza saved=pizzaDTO.save(p);
			Product saved = pizzaRepository.save(p);
			return pizzaMapper.toDto(saved);
		} else {
			throw new IllegalArgumentException();
			}
		}
	
	public void delete(Long id) {
		pizzaRepository.deleteById(id);
	}

	

}
