package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;

public interface CartService {

    public Cart findCartByUserId(Long userId);

    void removeCartItem(Long cartItemId ,Long userId);

    void clearCart(Long userId);
    

}
