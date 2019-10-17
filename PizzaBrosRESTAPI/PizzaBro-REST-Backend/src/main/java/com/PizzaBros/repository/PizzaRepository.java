package com.PizzaBros.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	List<Pizza> findAllByCategory(String )

}
