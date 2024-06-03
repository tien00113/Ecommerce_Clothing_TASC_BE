package com.tasc.clothing.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.model.Category;
import com.tasc.clothing.repository.CategoryRepository;

@Service
public class CategoryServiceImplement implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long parentCategoryId) throws Exception {
        Category parentCategory = null;
        
        if (parentCategoryId != null) {
            Optional<Category> parentCategoryOptional = categoryRepository.findById(parentCategoryId);
            if (parentCategoryOptional.isPresent()) {
                parentCategory = parentCategoryOptional.get();
            } else {
                throw new IllegalArgumentException("Parent category ID not found: " + parentCategoryId);
            }
        }

        Category category = new Category();
        category.setName(name);
        category.setParentCategory(parentCategory);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) throws Exception {

        Optional<Category> category1 = categoryRepository.findById(categoryId);

        if (category1.isEmpty()) {
            throw new Exception("Không tìm thấy phân loại có id " + categoryId);
        }

        Category oldCategory = category1.get();

        if(category.getName()!= null){
            oldCategory.setName(category.getName());
        }

        Category updatedCategory = categoryRepository.save(oldCategory);

        return updatedCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) throws Exception {
        
        Category category = findCategoryById(categoryId);

        categoryRepository.delete(category);

        return "Đã xóa thành công";
    }

    @Override
    public Category findCategoryById(Long categoryId) throws Exception {
        
        Optional<Category> opt = categoryRepository.findById(categoryId);

        if(opt.isEmpty()){
            throw new Exception("Không tìm thấy phân loại có id "+categoryId);
        }

        return opt.get();
    }

    @Override
    public List<Category> getAllCategory() {
        
        return categoryRepository.findAll();
    }

    @Override
    public Set<Long> getAllSubCategoryIds(Long categoryId) {
        Set<Long> categoryIds = new HashSet<>();
        addSubCategoryIds(categoryId, categoryIds);
        return categoryIds;
    }   


    private void addSubCategoryIds(Long categoryId, Set<Long> categoryIds) {
        categoryIds.add(categoryId);
        List<Category> subCategories = categoryRepository.findByParentCategoryId(categoryId);
        for (Category subCategory : subCategories) {
            addSubCategoryIds(subCategory.getId(), categoryIds);
        }
    }

}
