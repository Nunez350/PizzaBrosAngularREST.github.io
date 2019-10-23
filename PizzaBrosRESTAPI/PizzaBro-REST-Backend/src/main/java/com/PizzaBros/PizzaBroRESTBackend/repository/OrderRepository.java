package com.PizzaBros.PizzaBroRESTBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaBros.PizzaBroRESTBackend.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
