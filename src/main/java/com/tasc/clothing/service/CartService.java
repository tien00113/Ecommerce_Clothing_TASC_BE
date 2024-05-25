package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.request.CartItemRequest;

public interface CartService {

    public Cart addToCart(CartItemRequest cartItemId);

    public Cart findCartByUserId(Long userId);

    public Cart removeCartItem(Long cartItemId);

    
}
