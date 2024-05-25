package com.tasc.clothing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.repository.CartItemRepository;


@Service
public class CartItemServiceImplement implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository; 

    @Override
    @Transactional
    public CartItem addOrUpdateCartItem(Product product, Cart cart, String color, String size) {
        List<CartItem> existingItems = cartItemRepository.findByCartAndProductAndColorAndSize(cart, product, color, size);

        if (!existingItems.isEmpty()) {
            // Sản phẩm đã tồn tại, tăng số lượng
            CartItem existingItem = existingItems.get(0);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            return cartItemRepository.save(existingItem);
        } else {
            // Sản phẩm chưa tồn tại, thêm mới
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setCart(cart);
            newItem.setQuantity(1); // Bắt đầu với 1 sản phẩm
            newItem.setColor(color);
            newItem.setSize(size);
            return cartItemRepository.save(newItem);
        }
    }

    @Override
    @Transactional
    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        return cartItemRepository.findById(id)
                .map(cartItem -> {
                    cartItem.setProduct(updatedCartItem.getProduct());
                    cartItem.setCart(updatedCartItem.getCart());
                    cartItem.setQuantity(updatedCartItem.getQuantity());
                    cartItem.setPrice(updatedCartItem.getPrice());
                    cartItem.setUserId(updatedCartItem.getUserId());
                    cartItem.setColor(updatedCartItem.getColor());
                    cartItem.setSize(updatedCartItem.getSize());
                    return cartItemRepository.save(cartItem);
                })
                .orElseGet(() -> {
                    updatedCartItem.setId(id);
                    return cartItemRepository.save(updatedCartItem);
                });
    }

    @Override
    @Transactional
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    // Tìm CartItem theo ID
    public Optional<CartItem> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    
    
}
