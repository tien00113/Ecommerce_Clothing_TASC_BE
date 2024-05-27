package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
// import com.tasc.clothing.model.Product;
import com.tasc.clothing.model.User;

public interface CartItemService {
    
    // public CartItem addOrUpdateCartItem(Product product, Cart cart, String color, String size);

    public CartItem addOrUpdateCartItem(User user, Long productId, Cart cart, String color, String size);

    public String deleteCartItem(Long cartItemId);

    public CartItem updateCartItem(Long id, CartItem updatedCartItem);

    public CartItem increaseQuantity(Long itemId);
    public CartItem decreaseQuantity(Long itemId);
}
