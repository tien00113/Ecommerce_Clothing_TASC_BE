package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    public Cart findCartByUser(User user);
}
