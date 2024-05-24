package com.tasc.clothing.service;

import com.tasc.clothing.model.Order;

public interface Orderservice {
    public Order creatOrder(Order order);
    public Order DeleteOrder(Order order , Long Order_id);
    public Order EditOrder(Order order , Long Order_id);
    
}
