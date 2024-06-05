package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.User;
import com.tasc.clothing.request.CartItemRequest;
import com.tasc.clothing.service.CartItemService;
import com.tasc.clothing.service.CartService;
import com.tasc.clothing.service.UserService;


@RestController
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/addtocart")
    public ResponseEntity<CartItem> addOrUpdateCartItem(@RequestHeader("Authorization") String jwt ,@RequestBody CartItemRequest request) {

        User user = userService.findUserByJwt(jwt);
        CartItem cartItem = cartItemService.addOrUpdateCartItem(user , request);

        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/user/cart")
    public ResponseEntity<Cart> getUserCart(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        Cart cart = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
}

