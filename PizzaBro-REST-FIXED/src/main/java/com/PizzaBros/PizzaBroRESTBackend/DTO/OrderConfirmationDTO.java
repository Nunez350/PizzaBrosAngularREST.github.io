package com.PizzaBros.PizzaBroRESTBackend.DTO;

public class OrderConfirmationDTO {
	
	private String orderId;
	private String authCode;
	private double amount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderConfirmationDTO(String orderId, String authCode, double amount) {
		super();
		this.orderId = orderId;
		this.authCode = authCode;
		this.amount = amount;
	}

	public OrderConfirmationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
