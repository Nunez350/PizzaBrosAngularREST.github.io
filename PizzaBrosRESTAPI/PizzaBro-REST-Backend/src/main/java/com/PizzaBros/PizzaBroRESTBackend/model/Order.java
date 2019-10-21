package com.PizzaBros.PizzaBroRESTBackend.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.PizzaBros.PizzaBroRESTBackend.model.OrderLine;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orders_id")
	private Integer id;

	@Column(name = "customer_name")
	private String customer_name;
	private String address;

	private String card_number;
	private String card_expiry;
	private String card_security_code;
	private boolean submitted;
	private boolean delivered;
	private String auth_code;
	private Double amount;
	
	@OneToMany(mappedBy = "order")
	private Set<OrderLine> orderLines;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCard_expiry() {
		return card_expiry;
	}
	public void setCard_expiry(String card_expiry) {
		this.card_expiry = card_expiry;
	}
	public String getCard_security_code() {
		return card_security_code;
	}
	public void setCard_security_code(String card_security_code) {
		this.card_security_code = card_security_code;
	}
	public boolean isSubmitted() {
		return submitted;
	}
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}



	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}



	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerName=" + customer_name + ", address=" + address + ", cardNumber="
				+ card_number + ", cardExpiry=" + card_expiry + ", cardSecurityCode=" + card_security_code + ", submitted="
				+ submitted + ", shipped=" + delivered + ", authCode=" + auth_code + ", amount=" + amount + ", orderLines="
				+ orderLines + "]";
	}
	
}