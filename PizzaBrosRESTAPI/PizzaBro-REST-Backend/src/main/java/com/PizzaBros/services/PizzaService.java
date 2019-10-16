package com.PizzaBros.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	
	
	
	
	public List<Pizza> findAll(String category){
		List<Pizza> findAll = null;
		
		if (category)
	}
	
}
