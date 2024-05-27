package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Order;
import com.tasc.clothing.model.User;
import com.tasc.clothing.request.OrderRequest;
import com.tasc.clothing.service.OrderService;
import com.tasc.clothing.service.UserService;

@RestController
public class OrderController {

    @Autowired 
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("create/orders")
    public Order createOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);

        return orderService.createOrder(orderRequest, user);
    }
    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/orders/remove/{orderId}") // Sửa từ {order} thành {orderId}
    public ResponseEntity<String> removeCartItem(@PathVariable("orderId") Long orderId) {
        try {
            String message = orderService.deleteOrder(orderId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while removing the cart item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
