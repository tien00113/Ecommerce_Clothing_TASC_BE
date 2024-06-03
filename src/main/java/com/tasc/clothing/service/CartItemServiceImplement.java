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
import com.tasc.clothing.request.CartItemRequest;


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
    public CartItem addOrUpdateCartItem(User user, CartItemRequest cartItemRequest) {

        // Cart existCart = cartRepository.findCartByUserId(user.getId());
        // if (cartItemRequest.getCart() == null) {
        //     existCart = new Cart();
        //     existCart.setUser(user);
        //     existCart = cartRepository.save(existCart);
        // }

        // CartItem existingItem = cartItemRepository.findByCartAndProductIdAndColorAndSizeAndUserId(cartItemRequest.getCart(), cartItemRequest.getProductId(), cartItemRequest.getColor(), cartItemRequest.getSize(), user.getId());

        // if (existingItem != null) {
        //     existingItem.setQuantity(existingItem.getQuantity() + 1);
        //     existingItem.setPrice(existingItem.getProduct().getPrice() * existingItem.getQuantity());

        //     existCart.getCartItems().add(existingItem);
        //     existCart.setTotalPrice(existCart.getTotalPrice() + existingItem.getPrice()*existingItem.getQuantity());
        //     cartRepository.save(existCart);
        //     return cartItemRepository.save(existingItem);

        // } else {
        //     Product product = productService.findProductById(cartItemRequest.getProductId());

        //     CartItem newItem = new CartItem();

        //     newItem.setProduct(product);
        //     newItem.setCart(existCart);
        //     newItem.setQuantity(1);
        //     newItem.setColor(cartItemRequest.getColor());
        //     newItem.setSize(cartItemRequest.getSize());
        //     newItem.setPrice(product.getPrice());
        //     newItem.setUserId(user.getId());

        //     existCart.getCartItems().add(newItem);
        //     existCart.setTotalPrice(existCart.getTotalPrice() + newItem.getPrice()*newItem.getQuantity());
        //     cartRepository.save(existCart);
        //     return cartItemRepository.save(newItem);
        // }
        Cart cart = cartItemRequest.getCart();
        Long productId = cartItemRequest.getProductId();
        String color = cartItemRequest.getColor();
        String size = cartItemRequest.getSize();

        Cart existCart = cartRepository.findCartByUserId(user.getId());
        if (existCart == null) {
            existCart = new Cart();
            existCart.setUser(user);
            existCart = cartRepository.save(existCart);
        }

        CartItem existingItem = cartItemRepository.findByCartAndProductIdAndColorAndSizeAndUserId(existCart, productId, color, size, user.getId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            existingItem.setPrice(existingItem.getProduct().getPrice() * existingItem.getQuantity());

            existCart.getCartItems().add(existingItem);
            existCart.setTotalPrice(existCart.getTotalPrice() + existingItem.getPrice() * existingItem.getQuantity());
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
            existCart.setTotalPrice(existCart.getTotalPrice() + newItem.getPrice() * newItem.getQuantity());
            cartRepository.save(existCart);
            return cartItemRepository.save(newItem);
        }
    }

    @Override
    @Transactional
    public String deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
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
                .orElseThrow(() -> new RuntimeException("Không tìm thấy item có id: " + itemId));

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setPrice(cartItem.getPrice() + cartItem.getProduct().getPrice());
        return cartItemRepository.save(cartItem);
    }


    @Override
    @Transactional
    public CartItem decreaseQuantity(Long itemId) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy item có id: " + itemId));

        int currentQuantity = cartItem.getQuantity();
        if (currentQuantity > 1) {
            cartItem.setQuantity(currentQuantity - 1);
            cartItem.setPrice(cartItem.getPrice() - cartItem.getProduct().getPrice());

            cartItemRepository.save(cartItem);
        } else {
            Cart cart = cartRepository.findCartByUserId(cartItem.getUserId());

            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
            cartItem = null;
        }

        return cartItem;
    }
    
}