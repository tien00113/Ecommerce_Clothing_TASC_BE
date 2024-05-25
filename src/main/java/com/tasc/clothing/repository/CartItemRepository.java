package com.tasc.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.CartItem;
import com.tasc.clothing.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem , Long > {
    List<CartItem> findByCartAndProductAndColorAndSize(Cart cart, Product product, String color, String size);
}
