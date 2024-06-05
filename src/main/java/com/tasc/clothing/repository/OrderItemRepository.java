package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
