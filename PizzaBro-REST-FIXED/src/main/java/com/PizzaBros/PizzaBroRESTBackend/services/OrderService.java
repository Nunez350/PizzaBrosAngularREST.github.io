package com.PizzaBros.PizzaBroRESTBackend.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaBros.PizzaBroRESTBackend.DTO.CartDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.CheckoutDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.OrderConfirmationDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.OrderDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductSelectionDTO;
import com.PizzaBros.PizzaBroRESTBackend.model.Order;
import com.PizzaBros.PizzaBroRESTBackend.model.OrderLine;
import com.PizzaBros.PizzaBroRESTBackend.model.Product;
import com.PizzaBros.PizzaBroRESTBackend.repository.OrderRepository;
import com.PizzaBros.PizzaBroRESTBackend.repository.OrderlineRepository;
import com.PizzaBros.PizzaBroRESTBackend.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderlineRepository orderLineRepository;
	@Autowired
	private ProductRepository productRepository;

	public List<OrderDTO> fetchOrders() {
		List<Order> findAll = orderRepository.findAll();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		for (Order o : findAll) {
			OrderDTO d = mapOrder(o);
			orders.add(d);
		}
		return orders;
	}

	public OrderDTO shipOrders(String orderId) {
		Optional<Order> orderOptional = orderRepository.findById(Integer.valueOf(orderId));
		Order order = null;
		if (orderOptional.isPresent()) {
			order = orderOptional.get();
			order.setDelivered(true);
			orderRepository.save(order);
		}
		return mapOrder(order);
	}
	
	public OrderConfirmationDTO saveOrder(OrderDTO order, CartDTO cartDTO) {
		CheckoutDTO payment = order.getPayment();
		List<Product> allProducts = productRepository.findAll();
		Map<Long, Product> productsMap = allProducts.stream().collect(Collectors.toMap(Product::getId, p -> p));
		Order entity = new Order();
		entity.setAddress(order.getAddress());
		entity.setAmount(cartDTO.getTotalPrice());
		entity.setAuth_code(UUID.randomUUID().toString());
		entity.setCard_expiry(payment.getCardExpiry());
		entity.setCard_number(payment.getCardNumber());
		entity.setCard_security_code(payment.getCardSecurityCode());
		entity.setCustomer_name(order.getName());
		entity.setSubmitted(false);
		entity.setSubmitted(true);
		System.out.println(entity);
		Order savedOrder = orderRepository.save(entity);
		List<ProductSelectionDTO> products = order.getProducts();
		Set<OrderLine> orderLines = new HashSet<>();
		for (ProductSelectionDTO p : products) {
			orderLines.add(new OrderLine(productsMap.get(p.getProductId()), p.getQuantity(), savedOrder));
		}

		order.setOrderId("" + System.currentTimeMillis());
		order.setSubmitted(true);
		OrderConfirmationDTO dto = new OrderConfirmationDTO();

		dto.setAuthCode(savedOrder.getAuth_code());
		dto.setOrderId(savedOrder.getId() + "");
		dto.setAmount(savedOrder.getAmount());

		orderLineRepository.saveAll(orderLines);
		return dto;
	}

	private OrderDTO mapOrder(Order o) {
		OrderDTO d = new OrderDTO();
		if (o != null) {
			d.setAddress(o.getAddress());
			d.setName(o.getCustomer_name());
			d.setOrderId("" + o.getId());
			d.setShipped(o.isDelivered());
			d.setSubmitted(o.isSubmitted());
			Set<OrderLine> orderLines = o.getOrderLines();
			List<ProductSelectionDTO> products = new ArrayList<>();
			for (OrderLine line : orderLines) {
				Product product = line.getProduct();
				products.add(new ProductSelectionDTO(product.getId(), product.getName(), product.getPrice(), line.getQuantity()));
			}
			d.setProducts(products);
			d.setConfirmation(new OrderConfirmationDTO("" + o.getId(), o.getAuth_code(), o.getAmount()));
		}
		return d;
	}

}
