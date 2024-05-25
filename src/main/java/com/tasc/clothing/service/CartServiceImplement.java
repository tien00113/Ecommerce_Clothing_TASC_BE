package com.tasc.clothing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.repository.CartRepository;

@Service
public class CartServiceImplement implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findCartByUserId(Long userId) {

        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public Cart removeCartItem(Long cartItemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCartItem'");
    }
    
}
