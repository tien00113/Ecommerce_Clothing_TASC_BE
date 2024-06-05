package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
