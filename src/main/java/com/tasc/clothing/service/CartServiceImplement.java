package com.tasc.clothing.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImplement implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findCartByUserId(Long userId) {

        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public void removeCartItem(Long cartItemId, Long userId) {
    Cart cart = cartRepository.findCartByUserId(userId);
    if (cart != null) {
        Set<CartItem> cartItems = cart.getCartItems();
        System.out.println("Clearing cart items: " + cartItems.size());

        // Find the cart item by id
        CartItem itemToRemove = cartItems.stream()
                .filter(cartItem -> cartItemId.equals(cartItem.getId()))
                .findFirst()
                .orElse(null);

        // If the item is found, remove it from the set
        if (itemToRemove != null) {
            cartItems.remove(itemToRemove);
            cartRepository.save(cart); // Save the changes to the database
            System.out.println("Cart items after clearing: " + cartItems.size());
        } else {
            System.out.println("Cart item not found: " + cartItemId);
        }
    } else {
        System.out.println("No cart found for user: " + userId);
    }
}

    
    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart != null) {
            System.out.println("Clearing cart items: " + cart.getCartItems().size());
            cart.getCartItems().clear(); // Xóa các mục trong bộ nhớ
            cartRepository.save(cart); // Lưu thay đổi vào cơ sở dữ liệu
            System.out.println("Cart items after clearing: " + cart.getCartItems().size());
        } else {
            System.out.println("No cart found for user: " + userId);
        }
    }
    
}
