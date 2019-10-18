package com.PizzaBros.PizzaBroRESTBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaBros.PizzaBroRESTBackend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findAllById(String id );

	List<Product> findAllByCategory(String category);

	//List<Product> findAllProducts(String category);

	//Optional<Product> findByCategory(String category);

}
