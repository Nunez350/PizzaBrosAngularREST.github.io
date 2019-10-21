package com.PizzaBros.PizzaBroRESTBackend.mapper;

import org.springframework.stereotype.Component;

import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;

@Component
public class CustomerMapper {

	
	public Customer toEntity(CustomerDTO dto) {
		Customer customer = new Customer();		
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setUserName(dto.getUserName());
		customer.setPassword(dto.getPassword());
		customer.setEmail(dto.getEmail());
		customer.setPoints(dto.getPoints());
		customer.setAddress(dto.getAddress());
		return customer;
	}
	
	public CustomerDTO toDto(Customer entity) {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(entity.getCustomerId());
		customer.setFirstName(entity.getFirstName());
		customer.setLastName(entity.getLastName());
		customer.setUserName(entity.getUserName());
		customer.setPassword(entity.getPassword());
		customer.setEmail(entity.getEmail());
		customer.setPoints(entity.getPoints());
		customer.setAddress(entity.getAddress());
		return customer;
	}
	
	
}
