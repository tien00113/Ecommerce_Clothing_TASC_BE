package com.tasc.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasc.clothing.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByParentCategoryId(Long parentCategoryId);
}
