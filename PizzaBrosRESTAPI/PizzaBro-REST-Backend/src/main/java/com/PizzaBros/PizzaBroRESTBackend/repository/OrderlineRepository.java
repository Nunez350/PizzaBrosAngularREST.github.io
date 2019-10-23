package com.PizzaBros.PizzaBroRESTBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaBros.PizzaBroRESTBackend.model.OrderLine;

@Repository
public interface OrderlineRepository extends JpaRepository<OrderLine, Long> {
}
