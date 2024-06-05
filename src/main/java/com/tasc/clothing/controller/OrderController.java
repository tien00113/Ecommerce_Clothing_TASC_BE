package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.Order;
import com.tasc.clothing.model.User;
import com.tasc.clothing.model.Order.OrderStatus;
import com.tasc.clothing.request.OrderRequest;
import com.tasc.clothing.service.OrderService;
import com.tasc.clothing.service.UserService;

@RestController
public class OrderController {
    @Autowired 
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);

        Order order = orderService.createOrder(orderRequest, user);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @PostMapping("/order/update/{orderId}")
    public  ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus orderStatus){

        Order order = orderService.updateOrderStatus(orderId, orderStatus);

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
