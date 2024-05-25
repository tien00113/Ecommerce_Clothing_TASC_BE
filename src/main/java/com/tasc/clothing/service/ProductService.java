package com.tasc.clothing.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.request.ProductFilterRequest;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product) throws ProductException;
    public String deleteProduct(Long productId);

    public List<Product> getAllProduct();

    public Product findProductById(Long productId);
    
    public List<Product> findProductByCategory(Long categoryId);

    public Page<Product> getAllFilter(ProductFilterRequest productFilterRequest, Pageable pageable);
    
}
