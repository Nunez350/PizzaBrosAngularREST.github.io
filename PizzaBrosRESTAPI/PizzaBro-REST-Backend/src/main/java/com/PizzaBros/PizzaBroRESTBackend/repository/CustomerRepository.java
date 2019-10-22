
package com.PizzaBros.PizzaBroRESTBackend.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<CustomerDTO> findAllById(Long customerId );
    List<Customer> findAll();
    
}