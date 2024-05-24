package com.tasc.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tasc.clothing.model.Product;

@Repository
public interface ProRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findByCategory(Long categoryId);
}
