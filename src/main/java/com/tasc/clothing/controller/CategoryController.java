package com.tasc.clothing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.Category;
import com.tasc.clothing.request.CategoryRequest;
import com.tasc.clothing.service.CategoryService;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categorySerVice;

    @PostMapping("/admin/categorys")
    public ResponseEntity<Category> creatCategory(@RequestBody CategoryRequest categoryRequest, @RequestHeader("Authorization") String jwt) throws Exception{

        Category createdCategory = categorySerVice.createCategory(categoryRequest.getName(), categoryRequest.getParentCategoryId());

        return new ResponseEntity<Category>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/admin/categorys/{categoryId}")
    public Category updatCategory(@RequestBody Category category, @PathVariable Long categoryId) throws Exception{

        Category updatedCategory = categorySerVice.updateCategory(category, categoryId);

        return updatedCategory;
    }

    @DeleteMapping("/admin/categorys/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) throws Exception{

        String message = categorySerVice.deleteCategory(categoryId);

        return message;
    }

    @GetMapping("/allproduct/categorys")
    public List<Category> getAllCategory() throws Exception{

        return categorySerVice.getAllCategory();
    }
}
