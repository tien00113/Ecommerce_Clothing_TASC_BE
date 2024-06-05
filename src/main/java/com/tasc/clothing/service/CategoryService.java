package com.tasc.clothing.service;

import java.util.List;
import java.util.Set;

import com.tasc.clothing.model.Category;

public interface CategoryService {
    public Category createCategory(String name, Long parentCategoryId) throws Exception;

    public Category updateCategory(Category category, Long categoryId) throws Exception;

    public String deleteCategory(Long categoryId) throws Exception;

    public Category findCategoryById(Long categoryId) throws Exception;

    public List<Category> getAllCategory();

    public Set<Long> getAllSubCategoryIds(Long categoryId);
}
