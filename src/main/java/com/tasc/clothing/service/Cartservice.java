package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;

public interface Cartservice {
    public Cart createCart(Cart cart);
    public Cart deleteCart(Cart cart , Long cart_id);
    public Cart editCart(Cart cart , Long cart_id);

}
