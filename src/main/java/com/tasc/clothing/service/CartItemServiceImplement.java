package com.tasc.clothing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasc.clothing.controller.ResourceNotFoundException;
import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.model.User;
import com.tasc.clothing.repository.CartItemRepository;
import com.tasc.clothing.repository.CartRepository;


@Service
public class CartItemServiceImplement implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository; 

    @Autowired 
    private CartRepository cartRepository;

    @Autowired 
    private ProductService productService;


    @Override
    @Transactional
    public CartItem addOrUpdateCartItem(User user, Long productId, Cart cart, String color, String size) {

        Cart existCart = cartRepository.findCartByUserId(user.getId());
        if (cart == null) {
            existCart = new Cart();
            existCart.setUser(user);
            existCart = cartRepository.save(existCart);
        }

        CartItem existingItem = cartItemRepository.findByCartAndProductIdAndColorAndSizeAndUserId(cart, productId, color, size, user.getId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            existingItem.setPrice(existingItem.getProduct().getPrice() * existingItem.getQuantity());

            existCart.getCartItems().add(existingItem);
            existCart.setTotalPrice(existCart.getTotalPrice() + existingItem.getPrice()*existingItem.getQuantity());
            cartRepository.save(existCart);
            return cartItemRepository.save(existingItem);

        } else {
            Product product = productService.findProductById(productId);

            CartItem newItem = new CartItem();

            newItem.setProduct(product);
            newItem.setCart(existCart);
            newItem.setQuantity(1);
            newItem.setColor(color);
            newItem.setSize(size);
            newItem.setPrice(product.getPrice());
            newItem.setUserId(user.getId());

            existCart.getCartItems().add(newItem);
            existCart.setTotalPrice(existCart.getTotalPrice() + newItem.getPrice()*newItem.getQuantity());
            cartRepository.save(existCart);
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
    public String deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
        return "đã xóa item trong giỏ hàng";
    }


    // Tìm CartItem theo ID
    public Optional<CartItem> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    @Transactional
    public CartItem increaseQuantity(Long itemId) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + itemId));

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        return cartItemRepository.save(cartItem);
    }


    @Override
    @Transactional
    public CartItem decreaseQuantity(Long itemId) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + itemId));

        int currentQuantity = cartItem.getQuantity();
        if (currentQuantity > 1) {
            cartItem.setQuantity(currentQuantity - 1);
        } else {
            throw new RuntimeException("Quantity cannot be less than 1");
        }

        return cartItemRepository.save(cartItem);
    }


    
}
