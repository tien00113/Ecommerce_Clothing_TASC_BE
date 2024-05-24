package com.tasc.clothing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.Product;
import com.tasc.clothing.service.ProductService;

@RestController
public class CustomerController {
    @Autowired
    private ProductService productService;

    @GetMapping("/allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProduct();

        return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
    }
}
