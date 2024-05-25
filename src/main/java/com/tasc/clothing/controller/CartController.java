package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.repository.CartItemRepository;
import com.tasc.clothing.request.CartItemRequest;
import com.tasc.clothing.service.CartItemService;
import com.tasc.clothing.service.CartItemServiceImplement;


@RestController
public class CartController {
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("api/addToCart")
    public ResponseEntity<CartItem> addOrUpdateCartItem(@RequestBody CartItemRequest request) {
        CartItem cartItem = cartItemService.addOrUpdateCartItem(request.getProduct(), request.getCart(), request.getColor(), request.getSize());
        return ResponseEntity.ok(cartItem);
    }
}

