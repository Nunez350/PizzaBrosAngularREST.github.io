package com.PizzaBros.PizzaBroRESTBackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findAllById(Long CustomerId );

	List<Customer> findAll();

}


