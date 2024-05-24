package com.tasc.clothing.service;

import com.tasc.clothing.model.CartItem;

public interface Cartitemservice {
    public CartItem cretaCartitem(CartItem cartItem);
    public CartItem deleteCartitem(CartItem cartItem ,Long CartItem_id);
    public CartItem editCarttem(CartItem cartItem , Long CartItem_id);
}
