package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Long > {
    CartItem findByCartAndProductIdAndColorAndSizeAndUserId(Cart cart, Long productId, String color, String size, Long userId);
}
