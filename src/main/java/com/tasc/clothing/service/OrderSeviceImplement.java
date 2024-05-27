package com.tasc.clothing.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Order;
import com.tasc.clothing.model.User;
import com.tasc.clothing.model.Order.OrderStatus;
import com.tasc.clothing.repository.OrderRepository;
import com.tasc.clothing.request.OrderRequest;

@Service
public class OrderSeviceImplement implements OrderService  {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest orderRequest, User user) {
        Order order = new Order();
        
        // Lấy thông tin từ Cart
        order.setCartItems(orderRequest.getCart().getCartItems());
        order.setTotalPrice(orderRequest.getCart().getTotalPrice());
        order.setStatus(OrderStatus.PLACED);
        order.setCreateAt(LocalDateTime.now());
        order.setNote(orderRequest.getNote());
        order.setUpdateStatusAt(LocalDateTime.now());
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        
        return savedOrder;    
    }  
  
    @Override
    public String deleteOrder( Long orderId ) {
        orderRepository.deleteById(orderId);
        return "đã xóa item trong giỏ hàng";
    }

    @Override
    public Order updateOrderStatus(Long orderId ,  OrderStatus status) {
         Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            order.setUpdateStatusAt(LocalDateTime.now());
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

     
    
}
