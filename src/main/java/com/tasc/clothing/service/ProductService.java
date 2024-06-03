package com.tasc.clothing.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.request.ProductFilterRequest;

public interface ProductService {
    public Product createProduct(Product product) throws Exception;

    public Product updateProduct(Product product) throws ProductException;
    
    public void deleteProduct(Long productId);

    public List<Product> getAllProduct();

    public Product findProductById(Long productId);
    
    public List<Product> findProductByCategory(Long level1CategoryId, Long level2CategoryId, Long level3CategoryId);

    public Page<Product> getAllFilter(ProductFilterRequest productFilterRequest);

}

