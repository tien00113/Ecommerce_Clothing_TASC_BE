package com.tasc.clothing.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tasc.clothing.exception.ProductException;
import com.tasc.clothing.model.Category;
import com.tasc.clothing.model.Product;
import com.tasc.clothing.repository.ProRepository;
import com.tasc.clothing.request.ProductFilterRequest;


@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProRepository proRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Product createProduct(Product product) throws Exception{

        if (product.getCategory() != null || product.getCategory().getId() != null) {
            Category category = categoryService.findCategoryById(product.getCategory().getId());
            product.setCategory(category);
            
        } else {
            throw new Exception("Product phải có category");
        }

        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setActive(true);
        // newProduct.setDetails(product.getDetails());
        newProduct.setImages(product.getImages());
        newProduct.setCategory(product.getCategory());

        // if (product.getDetails() != null && !product.getDetails().isEmpty()) {
        // for (Map.Entry<String, String> entry : product.getDetails().entrySet()) {
        // newProduct.addDetail(entry.getKey(), entry.getValue());
        // }
        // }
        newProduct.setColor(product.getColor());
        newProduct.setSize(product.getSize());

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

        if (product.getColor() != null) {
            updateProduct.setColor(product.getColor());
        }
        if (product.getSize() != null) {
            updateProduct.setSize(product.getSize());
        }

        return proRepository.save(updateProduct);

    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = proRepository.findById(productId).orElse(null);

        product.setActive(false);

        proRepository.save(product);

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
    public Page<Product> getAllFilter(ProductFilterRequest productFilterRequest) {
        Specification<Product> specification = Specification.where(null);

        if (productFilterRequest.getMinPrice() != 0) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .greaterThanOrEqualTo(root.get("price"), productFilterRequest.getMinPrice()));
        }
        if (productFilterRequest.getMaxPrice() != 0) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .lessThanOrEqualTo(root.get("price"), productFilterRequest.getMaxPrice()));
        }
        // if (productFilterRequest.getCategoryId() != null) {
        // specification = specification.and((root, query, criteriaBuilder) ->
        // criteriaBuilder
        // .equal(root.get("category").get("id"),
        // productFilterRequest.getCategoryId()));
        // }
        // if (productFilterRequest.getColor() != null) {
        // specification = specification.and((root, query, criteriaBuilder) -> {
        // MapJoin<Product, String, String> detailsJoin = root.joinMap("details");
        // return criteriaBuilder.and(
        // criteriaBuilder.equal(detailsJoin.key(), "color"),
        // criteriaBuilder.like(detailsJoin.value(), "%" +
        // productFilterRequest.getColor() + "%"));
        // });
        // }

        if (productFilterRequest.getColor() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("color"), "%" + productFilterRequest.getColor() + "%");
            });
        }

        if (productFilterRequest.getCategoryId() != null) {
            Set<Long> categoryIds = categoryService.getAllSubCategoryIds(productFilterRequest.getCategoryId());
            specification = specification.and((root, query, criteriaBuilder) -> 
                root.get("category").get("id").in(categoryIds));
        }

        Pageable pageable = PageRequest.of(
                productFilterRequest.getPageableDTO().getPage(),
                productFilterRequest.getPageableDTO().getSize(),
                Sort.by(productFilterRequest.getPageableDTO().getSort().stream()
                        .map(sortDTO -> new Sort.Order(Sort.Direction.fromString(sortDTO.getDirection()),
                                sortDTO.getProperty()))
                        .collect(Collectors.toList())));

        return proRepository.findAll(specification, pageable);
    }

    @Override
    public List<Product> findProductByCategory(Long level1CategoryId, Long level2CategoryId, Long level3CategoryId) {
        return null;
    }

}