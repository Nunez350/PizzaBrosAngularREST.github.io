package com.PizzaBros.PizzaBroRESTBackend.DTO;

import java.util.List;

import com.PizzaBros.PizzaBroRESTBackend.DTO.CheckoutDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductSelectionDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.OrderConfirmationDTO;



public class OrderDTO {
	
	private String orderId;
	private String name;
	private String address;
	private CheckoutDTO payment;
	private boolean submitted;
	private boolean shipped;
	private OrderConfirmationDTO confirmation;
	private List<ProductSelectionDTO> products;
	
	
	
	
	public String getOrderId() {
		return orderId;
	}




	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public CheckoutDTO getPayment() {
		return payment;
	}




	public void setPayment(CheckoutDTO payment) {
		this.payment = payment;
	}




	public boolean isSubmitted() {
		return submitted;
	}




	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}




	public boolean isShipped() {
		return shipped;
	}




	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}




	public OrderConfirmationDTO getConfirmation() {
		return confirmation;
	}




	public void setConfirmation(OrderConfirmationDTO confirmation) {
		this.confirmation = confirmation;
	}




	public List<ProductSelectionDTO> getProducts() {
		return products;
	}




	public void setProducts(List<ProductSelectionDTO> products) {
		this.products = products;
	}




	@Override
	public String toString() {
		return "OrderDTO [address=" + address + ", products=" + products + ", name=" + name + ", payment=" + payment + "]";
	}
	

}
