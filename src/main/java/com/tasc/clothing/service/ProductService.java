package com.tasc.clothing.service;

import java.util.List;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product) throws ProductException;
    public String deleteProduct(Long productId);

    public List<Product> getAllProduct();

    public Product findProductById(Long productId);
    
    public List<Product> findProductByCategory(Long categoryId);
    
}
