package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.Category;

public interface CartRepository extends JpaRepository<Category, Long>{
    
}
