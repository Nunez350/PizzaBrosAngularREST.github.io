package com.PizzaBros.PizzaBroRESTBackend.controller;


import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaBros.PizzaBroRESTBackend.DTO.*;
import com.PizzaBros.PizzaBroRESTBackend.services.OrderService;


@RestController
@RequestMapping("/api")
public class SessionController {

	private Map<String, CartDTO> cartStore = new HashMap<>();
	private Map<String, CheckoutDTO> checkoutStore = new HashMap<>();

	@Autowired
	private OrderService orderService;

	@GetMapping("/session/cart")
	public ResponseEntity<CartDTO> getCart(@CookieValue(name = "JSESSIONID", required = false) String cookieValue) {
		CartDTO cartDTO = cartStore.get(cookieValue);
		if (cartDTO == null) {
			cartDTO = new CartDTO();
		}
		cartStore.put(cookieValue, cartDTO);
		return ResponseEntity.status(HttpStatus.OK).body(cartDTO);
	}

	@PostMapping("/session/cart")
	public ResponseEntity<ProductSelectionDTO[]> create(@RequestBody @Valid ProductSelectionDTO[] products,
			@CookieValue(name = "JSESSIONID", required = false) String cookieValue) throws URISyntaxException {
		CartDTO cartDTO = cartStore.get(cookieValue);
		if (cartDTO == null) {
			cartDTO = new CartDTO();
		}
		cartDTO.setSelections(products);
		double total = 0.0;
		for (ProductSelectionDTO s : products) {
			total += s.getQuantity() * s.getPrice();
		}
		cartDTO.setTotalPrice(total);

		cartStore.put(cookieValue, cartDTO);
		return ResponseEntity.ok().body(products);
	}

	@PostMapping("/session/checkout")
	public ResponseEntity<CheckoutDTO> checkout(@CookieValue(name = "JSESSIONID", required = false) String cookieValue,
			@RequestBody CheckoutDTO checkout) {
		checkoutStore.put(cookieValue, checkout);
		return ResponseEntity.status(HttpStatus.OK).body(checkout);
	}

	@GetMapping("/session/checkout")
	public ResponseEntity<CheckoutDTO> checkout(
			@CookieValue(name = "JSESSIONID", required = false) String cookieValue) {
		CheckoutDTO checkoutDTO = checkoutStore.get(cookieValue);
		return ResponseEntity.status(HttpStatus.OK).body(checkoutDTO);
	}

	@PostMapping("/orders")
	public ResponseEntity<OrderConfirmationDTO> saveOrder(
			@CookieValue(name = "JSESSIONID", required = false) String cookieValue, @RequestBody OrderDTO order) {
		CartDTO cartDTO = cartStore.get(cookieValue);
		OrderConfirmationDTO saveOrder = orderService.saveOrder(order, cartDTO);
		cartStore.remove(cookieValue);
		checkoutStore.remove(cookieValue);
		return ResponseEntity.ok().body(saveOrder);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDTO>> fetchOrders(
			@CookieValue(name = "JSESSIONID", required = false) String cookieValue) {
		List<OrderDTO> orders = orderService.fetchOrders();
		return ResponseEntity.ok().body(orders);
	}

	@PostMapping("/orders/{id}")
	public ResponseEntity<OrderDTO> shipOrders(@CookieValue(name = "JSESSIONID", required = false) String cookieValue,
			@PathVariable(name = "id") String orderId) {
		OrderDTO order = orderService.shipOrders(orderId);
		return ResponseEntity.ok().body(order);
	}
}
