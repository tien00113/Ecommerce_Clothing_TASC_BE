package com.tasc.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Order;
import com.tasc.clothing.model.OrderItem;
import com.tasc.clothing.model.User;
import com.tasc.clothing.model.Order.OrderStatus;
import com.tasc.clothing.repository.OrderItemRepository;
import com.tasc.clothing.repository.OrderRepository;
import com.tasc.clothing.request.OrderRequest;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest orderRequest, User user) {
        Cart cart = cartService.findCartByUserId(user.getId());

        Set<OrderItem> orderItems = new HashSet<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setColor(cartItem.getColor());
            orderItem.setSize(cartItem.getSize());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setUserId(cartItem.getUserId());
            
            OrderItem createdOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }

        Order createOrder = new Order();

        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setNote(orderRequest.getNote());
        createOrder.setAddress(orderRequest.getAddress());
        createOrder.setStatus(OrderStatus.PLACED);
        createOrder.setCreateAt(LocalDateTime.now());
        createOrder.setUpdateStatusAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(createOrder);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder);

            orderItemRepository.save(orderItem);
        }

        return savedOrder;

    }

    @Override
    public String deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return "đã xóa đơn hàng";
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
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
