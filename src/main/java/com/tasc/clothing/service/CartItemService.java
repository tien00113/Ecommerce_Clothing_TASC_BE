package com.tasc.clothing.service;

import com.tasc.clothing.model.CartItem;

public interface CartItemService {
    public CartItem cretaCartitem(CartItem cartItem);
    public CartItem deleteCartitem(CartItem cartItem ,Long cartItemId);
    public CartItem updateCarttem(CartItem cartItem , Long cartItemId);
}
