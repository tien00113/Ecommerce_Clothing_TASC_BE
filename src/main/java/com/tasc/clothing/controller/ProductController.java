package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.request.ProductFilterRequest;
import com.tasc.clothing.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/allproduct")
    public Page<Product> getAllProducts(
            @RequestBody ProductFilterRequest productFilterRequest,
            Pageable pageable) {
        return productService.getAllFilter(productFilterRequest, pageable);
    }

    @PostMapping("/admin/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdproduct = productService.createProduct(product);

        return new ResponseEntity<Product>(createdproduct, HttpStatus.CREATED);
    }

    @PutMapping("/admin/product/update")
    public ResponseEntity<Product> updatedProduct(@RequestBody Product product) throws ProductException{
        Product updateProduct = productService.updateProduct(product);

        return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/remove")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) throws ProductException{
        return new ResponseEntity<String>(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
