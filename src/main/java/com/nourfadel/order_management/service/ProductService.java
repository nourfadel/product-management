package com.nourfadel.order_management.service;

import com.nourfadel.order_management.Repository.CategoryRepository;
import com.nourfadel.order_management.Repository.ProductRepository;
import com.nourfadel.order_management.dto.request.CategoryRequest;
import com.nourfadel.order_management.dto.request.ProductRequest;
import com.nourfadel.order_management.dto.response.ProductResponse;
import com.nourfadel.order_management.entity.Category;
import com.nourfadel.order_management.entity.Product;
import com.nourfadel.order_management.exception.ProductNotFoundException;
import com.nourfadel.order_management.exception.ResourceNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // create product
    public ProductResponse create(ProductRequest request){

        Set<Category> categories =resolveCategories(request.getCategories());

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .categories(categories)
                .build();

        productRepository.save(product);

        return mapToResponse(product);
    }


    private Set<Category> resolveCategories(List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return new HashSet<>();
        }
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
        if (categories.size() != new HashSet<>(categoryIds).size()) {
            Set<Long> found = categories.stream().map(Category::getId).collect(Collectors.toSet());
            List<Long> missing = categoryIds.stream().filter(cid -> !found.contains(cid)).toList();
            throw new ResourceNotFoundException("Categories not found");
        }
        return categories;
    }


    private ProductResponse mapToResponse(Product request){
        return ProductResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .categories(request.getCategories().stream().map(Category::getId).collect(Collectors.toSet()) )
                .build();
    }
    // getAll products
    public List<ProductResponse> findAll(){
        return productRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    // get product
    public ProductResponse findById(Long id){
        return mapToResponse(getOrThrow(id));
    }

    // update product
    public ProductResponse update(Long id,ProductRequest request){
        Product product = getOrThrow(id);

        Set<Category> categories = resolveCategories(request.getCategories());

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategories(categories);

        return mapToResponse(productRepository.save(product));
    }

    // delete product
    public void delete(Long id){
        Product product = getOrThrow(id);
        productRepository.delete(product);
    }

    private Product getOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found: "+id));
    }
}




















