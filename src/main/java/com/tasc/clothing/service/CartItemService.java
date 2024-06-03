package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.User;
import com.tasc.clothing.request.CartItemRequest;

public interface CartItemService {
    public CartItem addOrUpdateCartItem(User user, CartItemRequest cartItemRequest);

    public String deleteCartItem(Long cartItemId);

    public CartItem increaseQuantity(Long itemId);

    public CartItem decreaseQuantity(Long itemId);
}