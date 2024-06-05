package com.tasc.clothing.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.repository.CartItemRepository;
import com.tasc.clothing.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImplement implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart findCartByUserId(Long userId) {

        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public String removeCartItem(Long cartItemId, Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart != null) {
            Set<CartItem> cartItems = cart.getCartItems();

            CartItem itemToRemove = cartItems.stream()
                    .filter(cartItem -> cartItemId.equals(cartItem.getId()))
                    .findFirst()
                    .orElse(null);

            if (itemToRemove != null) {
                cartItems.remove(itemToRemove);
                return "Đã xóa item thành công";
            } else {
                return "không tìm thấy item có id: {}" + cartItemId;
            }
        } else {
            return "cart user null: {}" + userId;
        }
    }

    @Override
    @Transactional
    public String clearCart(Long userId) {

        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Không tìm thấy giỏ hàng cho người dùng có id: " + userId);
        }

        cartItemRepository.deleteByCartId(cart.getId());

        return "đã xóa giỏ hàng thành công";
    }

}
