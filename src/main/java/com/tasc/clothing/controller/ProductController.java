package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdproduct = productService.createProduct(product);

        return new ResponseEntity<Product>(createdproduct, HttpStatus.CREATED);
    }

    @PutMapping("/product/update")
    public ResponseEntity<Product> updatedProduct(@RequestBody Product product) throws ProductException{
        Product updateProduct = productService.updateProduct(product);

        return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);

    }
}
