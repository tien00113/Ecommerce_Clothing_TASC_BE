package com.tasc.clothing.service;
import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Order;
import com.tasc.clothing.model.User;
import com.tasc.clothing.model.Order.OrderStatus;
import com.tasc.clothing.request.OrderRequest;

public interface OrderService {
    public Order createOrder(OrderRequest orderRequest, User user);
    public String deleteOrder(Long orderId);
    public Order updateOrderStatus(Long orderId, OrderStatus status);
    public Order getOrderById(Long orderId);
    
}
