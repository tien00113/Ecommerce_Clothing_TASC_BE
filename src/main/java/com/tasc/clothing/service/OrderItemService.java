package com.tasc.clothing.service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.OrderItem;
import com.tasc.clothing.model.User;

public interface OrderItemService {
    public OrderItem addOrderItem();

    public void deleteCartItem(Long cartItemId);
}
