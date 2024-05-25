package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;

public interface CartService {

    public Cart findCartByUserId(Long userId);

    public Cart removeCartItem(Long cartItemId);

}
