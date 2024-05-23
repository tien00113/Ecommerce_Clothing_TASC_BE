package com.tasc.clothing.service;

import com.tasc.clothing.model.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product, Long productId);
    public String deleteProduct(Long productId);
    
}
