package com.PizzaBros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaBros.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	List<Pizza> findAllById(String id );

}
