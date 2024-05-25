package com.tasc.clothing.service;

import com.tasc.clothing.model.Order;

public interface OrderService {
    public Order creatOrder(Order order);
    public Order deleteOrder(Order order , Long orderId);
    public Order updateOrder(Order order , Long orderId);
    
}
