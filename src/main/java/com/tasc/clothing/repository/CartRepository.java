package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
    public Cart findCartByUserId(Long userId);
}
