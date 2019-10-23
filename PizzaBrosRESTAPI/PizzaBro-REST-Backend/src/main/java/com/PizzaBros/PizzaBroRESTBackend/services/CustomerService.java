package com.PizzaBros.PizzaBroRESTBackend.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.DTO.ProductDTO;
import com.PizzaBros.PizzaBroRESTBackend.mapper.CustomerMapper;
import com.PizzaBros.PizzaBroRESTBackend.mapper.ProductMapper;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;
import com.PizzaBros.PizzaBroRESTBackend.model.Product;
import com.PizzaBros.PizzaBroRESTBackend.services.CustomerService;
import com.PizzaBros.PizzaBroRESTBackend.repository.CustomerRepository;


@Service
@Transactional
public class CustomerService {
	
	private static List<CustomerDTO> customers = new ArrayList<>();

		@Autowired
		private CustomerRepository customerRepository;
		
		@Autowired
		private CustomerMapper customerMapper;
		

		
//		public List<CustomerDTO> findAll(Long id) {
//			List<Customer> findAll = null;
//			
//			if (id != null && !id.isEmpty()) {
//				findAll = customerRepository.findAllById(id);
//			} else {
//				findAll = customerRepository.findAll();
//			}
//			return findAll.stream().map(c -> customerMapper.toDto(c)).collect(Collectors.toList());
//		}
//		
		
		public List<CustomerDTO> findAll(Long id) {
			List<Customer> findAll;
			
			if (id != null) {
				findAll = customerRepository.findAllById(id);
			} else {
				findAll = customerRepository.findAll();
			}
			return findAll.stream().map(c -> customerMapper.toDto(c)).collect(Collectors.toList());
		}
		
		

		public CustomerDTO findOne(Long Id) {
			Optional<Customer> customerOp = customerRepository.findById(Id);
			if (customerOp.isPresent()) {
				return customerMapper.toDto(customerOp.get());
			}
			return null;
		}
		
		public CustomerDTO save(CustomerDTO customer) {
			Customer entity = customerMapper.toEntity(customer);
			Customer saved = customerRepository.save(entity);
		return customerMapper.toDto(saved);
		}
		
		
		public CustomerDTO update(CustomerDTO customer, Long id) {
			Optional<Customer> findById = customerRepository.findById(id);
			if (findById.isPresent()) {
				Customer c = findById.get();
				c.setFirstName(customer.getFirstName());
				c.setLastName(customer.getLastName());
				c.setUserName(customer.getUserName());
				c.setEmail(customer.getEmail());
				c.setPoints(customer.getPoints());
				c.setAddress(customer.getAddress());
				
				
				Customer saved = customerRepository.save(c);
			
				return customerMapper.toDto(saved);
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		public void delete(Long id) {
			customerRepository.deleteById(id);
		}
}




