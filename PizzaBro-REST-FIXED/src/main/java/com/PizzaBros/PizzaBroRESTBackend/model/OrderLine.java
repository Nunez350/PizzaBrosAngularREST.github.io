package com.PizzaBros.PizzaBroRESTBackend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.PizzaBros.PizzaBroRESTBackend.model.Product;
import com.PizzaBros.PizzaBroRESTBackend.model.Order;



@Entity
@Table(name = "orderline")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	private Product product;
	
	private Integer quantity;
	

	@ManyToOne
	@JoinColumn(name = "orders_id")
	private Order order;
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

//	public OrderLine(Integer id, Product product, Integer quantity, Order order) {
//		super();
//		this.id = id;
//		this.product = product;
//		this.quantity = quantity;
//		this.order = order;
//	}

	public OrderLine() {
		super();
	}


	
	public OrderLine(Product product, Integer quantity, Order order) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.order = order;
	}


	
	
}