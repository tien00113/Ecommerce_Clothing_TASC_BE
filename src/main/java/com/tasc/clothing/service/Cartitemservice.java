package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.User;

public interface CartItemService {
    public CartItem addOrUpdateCartItem(User user, Long productId, Cart cart, String color, String size);

    public String deleteCartItem(Long cartItemId);

    public CartItem increaseQuantity(Long itemId);

    public CartItem decreaseQuantity(Long itemId);
}