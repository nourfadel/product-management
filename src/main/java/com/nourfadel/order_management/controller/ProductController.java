package com.nourfadel.order_management.controller;


import com.nourfadel.order_management.dto.request.ProductRequest;
import com.nourfadel.order_management.dto.response.ProductResponse;
import com.nourfadel.order_management.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // create product
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        ProductResponse created = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // find product
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    // find all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProduct(){
        return ResponseEntity.ok(productService.findAll());
    }

    // update product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody ProductRequest request
            ){
        return ResponseEntity.ok(productService.update(productId,request));
    }

    // delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

}
