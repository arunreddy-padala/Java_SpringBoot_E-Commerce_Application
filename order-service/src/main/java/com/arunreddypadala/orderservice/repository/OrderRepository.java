package com.arunreddypadala.orderservice.repository;

import com.arunreddypadala.orderservice.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
