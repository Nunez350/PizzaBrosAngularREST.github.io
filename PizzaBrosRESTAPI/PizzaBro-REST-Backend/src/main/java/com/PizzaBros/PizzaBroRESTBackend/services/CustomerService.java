package com.PizzaBros.PizzaBroRESTBackend.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;
import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.mapper.CustomerMapper;
import com.PizzaBros.PizzaBroRESTBackend.repository.CustomerRepository;




@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerMapper customerMapper;
		
	/*public List<CustomerDTO> fetchCustomers() {
		List<Customer> findAll = customerRepository.findAll();
		List<CustomerDTO> Customer = new ArrayList<CustomerDTO>();
		for (Customer c : findAll) {
			CustomerDTO d = mapCustomer(c);
			Customer.add(d);
		}
		return Customer;
	}
		
	private CustomerDTO mapCustomer(Customer c) {
		return null;
	}*/

	public List<CustomerDTO> findAll(String id) {
		List<Customer> findAll = null;
		
		if (id != null && !id.isEmpty()) {
			findAll = customerRepository.findAllById(id);
		} else {
			findAll = customerRepository.findAll();
		}
		return findAll.stream().map(c -> customerMapper.toDto(c)).collect(Collectors.toList());
	}
		public CustomerDTO save(CustomerDTO customer) {
			Customer entity = customerMapper.toEntity(customer);	
			Customer saved = customerRepository.save(entity);
			return customerMapper.toDto(saved);
		}
		
		public CustomerDTO update(CustomerDTO customer, String CustomerId) {
			Optional<Customer> findById = customerRepository.findById(CustomerId);
			if (findById.isPresent()) {
				Customer c = findById.get();
				c.setFirstName(customer.getFirstName());
				c.setLastName(customer.getLastName());
				c.setUserName(customer.getUserName());
				c.setEmail(customer.getEmail());
				c.setPassword(customer.getPassword());
				c.setPoints(customer.getPoints());
				c.setAddress(customer.getAddress());
				
				Customer saved = customerRepository.save(c);
				return customerMapper.toDto(saved);
			} else {
				throw new IllegalArgumentException();
				}
			}
		
		
		
		public void delete(String CustomerId) {
			customerRepository.deleteById(CustomerId);
		}


		/*public List<CustomerDTO> findCustomerId(Long CustomerId) {
			// TODO Auto-generated method stub
			return null;
		}
		public List<CustomerDTO> findAll(Long customerId) {
			// TODO Auto-generated method stub
			
			return null;
		
		}*/
		
}






