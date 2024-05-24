package com.tasc.clothing.service;


import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.repository.ProductRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImplement implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductException{
        Optional<Product> esxistProduct = productRepo.findById(product.getId());

        if(esxistProduct.isEmpty()){
            throw new ProductException("Không tìm thấy sản phẩm có ID là: "+product.getId());
        } 

        Product updateProduct = esxistProduct.get();

        if(product.getName()!= null){
            updateProduct.setName(product.getName());
        }
        if(product.getDescription()!= null){
            updateProduct.setDescription(product.getDescription());
        }
        if(product.getImage()!= null){
            updateProduct.setImage(product.getImage());
        }
        if(product.getPrice()!= 0){
            updateProduct.setPrice(product.getPrice());
        }

        if(product.getDetails() != null) {
            updateProduct.setDetails(product.getDetails());
        }
        return productRepo.save(updateProduct);
    }

    @Override
    public String deleteProduct(Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }
    
}
