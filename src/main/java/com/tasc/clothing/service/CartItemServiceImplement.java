package com.tasc.clothing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.model.User;
import com.tasc.clothing.repository.CartItemRepository;
import com.tasc.clothing.repository.CartRepository;

@Service
public class CartItemServiceImplement implements CartItemService {

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
