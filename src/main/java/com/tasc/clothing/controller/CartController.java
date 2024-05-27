package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        CartItem cartItem = cartItemService.addOrUpdateCartItem(user, request.getProductId(), request.getCart(), request.getColor(), request.getSize());
        return ResponseEntity.ok(cartItem);
    }
 
    @GetMapping("/user/cart")
    public ResponseEntity<Cart> getUserCart(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        Cart cart = cartService.findCartByUserId(user.getId());

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @DeleteMapping("/user/cart/clear")
    public ResponseEntity<?> clearUserCart(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        if (user != null) {
            cartService.clearCart(user.getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @DeleteMapping("/cart/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable("cartItemId") Long cartItemId) {
        try {
            String message = cartItemService.deleteCartItem(cartItemId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while removing the cart item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/{itemId}/increase")
    public ResponseEntity<CartItem> increaseCartItemQuantity(@PathVariable Long itemId) {
        try {
            CartItem updatedCartItem = cartItemService.increaseQuantity(itemId);
            return ResponseEntity.ok(updatedCartItem);
        } catch (RuntimeException e) {
            // Handle the exception properly here
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    // Endpoint to decrease the quantity of a cart item
    @PatchMapping("/{itemId}/decrease")
    public ResponseEntity<?> decreaseCartItemQuantity(@PathVariable Long itemId) {
        try {
            CartItem updatedCartItem = cartItemService.decreaseQuantity(itemId);
            return ResponseEntity.ok(updatedCartItem);
        } catch (RuntimeException e) {
            // Handle the exception properly here
            // For example, if the quantity cannot be less than 1, you might return a specific status code and message
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot decrease quantity below 1");
        }
    }
}

