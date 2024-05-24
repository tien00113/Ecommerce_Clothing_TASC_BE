package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasc.clothing.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    
}
