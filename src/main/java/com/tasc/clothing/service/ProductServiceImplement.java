package com.tasc.clothing.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.repository.ProRepository;
import com.tasc.clothing.request.ProductFilterRequest;

import jakarta.persistence.criteria.MapJoin;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProRepository proRepository;

    @Override
    public Product createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setDetails(product.getDetails());
        newProduct.setImages(product.getImages());
        return proRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product product) throws ProductException {
        Optional<Product> existProduct = proRepository.findById(product.getId());

        if (existProduct.isEmpty()) {
            throw new ProductException("Không tìm thấy sản phẩm có ID là: " + product.getId());
        }

        Product updateProduct = existProduct.get();

        if (product.getName() != null) {
            updateProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            updateProduct.setDescription(product.getDescription());
        }
        if (product.getImages() != null) {
            updateProduct.setImages(product.getImages());
        }
        if (product.getPrice() != 0) {
            updateProduct.setPrice(product.getPrice());
        }

        if (product.getDetails() != null) {
            updateProduct.setDetails(product.getDetails());
        }

        return proRepository.save(updateProduct);

    }

    @Override
    public String deleteProduct(Long productId) {
        proRepository.deleteById(productId);

        return ("deleted product successfully");
    }

    @Override
    public List<Product> getAllProduct() {
        return proRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) {
        return proRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findProductByCategory(Long categoryId) {
        return proRepository.findByCategoryId(categoryId);
    }

    @Override
    public Page<Product> getAllFilter(ProductFilterRequest productFilterRequest, Pageable pageable) {
        Specification<Product> specification = Specification.where(null);

        if (productFilterRequest.getMinPrice() != 0) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .greaterThanOrEqualTo(root.get("price"), productFilterRequest.getMinPrice()));
        }
        if (productFilterRequest.getMaxPrice() != 0) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .lessThanOrEqualTo(root.get("price"), productFilterRequest.getMaxPrice()));
        }
        if (productFilterRequest.getCategoryId() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("category").get("id"), productFilterRequest.getCategoryId()));
        }
        if (productFilterRequest.getColor() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                MapJoin<Product, String, String> detailsJoin = root.joinMap("details");
                return criteriaBuilder.and(
                        criteriaBuilder.equal(detailsJoin.key(), "color"),
                        criteriaBuilder.like(detailsJoin.value(), "%" + productFilterRequest.getColor() + "%"));
            });
        }

        return proRepository.findAll(specification, pageable);
    }

    

}