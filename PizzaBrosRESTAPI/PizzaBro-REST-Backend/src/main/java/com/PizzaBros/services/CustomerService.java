package com.PizzaBros.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaBros.model.Customer;
import com.test.dto.CustomerDTO;
import com.test.dto.StudioDTO;
import com.test.model.Customer;
import com.test.model.Studio;



@Service
@Transactional
public class CustomerService {

		@Autowired
		private CustomerRepository CustomerRepository;
		
		public List<CustomerDTO> findAll(String category) {
			List<Customer> findAll = null;
			
			if (category != null && !category.isEmpty()) {
				findAll = customerRepository.findAllByCategory(category);
			} else {
				findAll = customerRepository.findAll();
			}
			return findAll.stream().map(p -> customerMapper.toDto(p)).collect(Collectors.toList());
		}
		
		
		public CustomerDTO findOne(Long id) {
			Optional<Customer> customerOp = CustomerRepository.findById(id);
			if (customerOp.isPresent()) {
				return customerMapper.toDto(customerOp.get());
			}
			return null;
		}
		public PizzaDTO save(PizzaDTO customer) {
			Pizza entity = pizzaMapper.toEntity(customer);	
			Pizza saved = pizzaRepository.save(entity);
			return pizzaMapper.toDto(saved);
		}
		
		public CustomerDTO update(CustomerDTO customer, Long id) {
			Optional<Customer> findById = customerRepository.findById(id);
			if (findById.isPresent()) {
				Customer c = findById.get();
				c.setFirstName(customer.getFirstName());
				c.setLastName(customer.getLastName());
				c.setUsername(customer.getUsername());
				c.setEmail(customer.getEmail());
				c.setPrice(customer.getPrice());
				c.setDescription(customer.getDescription());
				
				c.setPoints(customer.getPoints());
				c.setAddress(customer.getAddress());
				Customersaved = customerRepository.save(c);
				return customerMapper.toDto(saved);
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		public void delete(Long id) {
			customerRepository.deleteById(id);
		}
		

}




