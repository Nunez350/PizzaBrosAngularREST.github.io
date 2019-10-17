package com.PizzaBros.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaBros.DTO.PizzaDTO;
import com.PizzaBros.services.PizzaService;


@RestController
@RequestMapping("/api")
public class PizzaController {
	private final PizzaService pizzaService;
	public PizzaController(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}
	
	@GetMapping("/pizza")
	public List<PizzaDTO> getAll(@RequestParam(name = "category", required = false) String category,@RequestParam(name = "related", required = false) boolean related, @RequestParam(name = "metadata", required = false) boolean metadata) {
		return pizzaService.findAll(category);
	}
}
