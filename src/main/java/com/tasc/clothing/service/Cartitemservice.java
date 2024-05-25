package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.User;

public interface CartItemService {
    
    public CartItem addOrUpdateCartItem(User user, Long productId, Cart cart, String color, String size);

    public void deleteCartItem(Long cartItemId);

    public CartItem updateCartItem(Long id, CartItem updatedCartItem);
}
