package com.nourfadel.order_management.controller;

import com.nourfadel.order_management.dto.request.CategoryRequest;
import com.nourfadel.order_management.dto.response.CategoryResponse;
import com.nourfadel.order_management.entity.Category;
import com.nourfadel.order_management.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // create category
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request) throws BadRequestException {
        CategoryResponse created = categoryService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // find category
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findCategory(@PathVariable Long categoryId){
        Category category = categoryService.findById(categoryId);

        return ResponseEntity.ok(category);
    }

    // find all category
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    // update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest request) throws BadRequestException {
        CategoryResponse updatedCategory = categoryService.update(categoryId,request);

        return ResponseEntity.ok(updatedCategory);
    }

    // delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) throws BadRequestException {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

}












