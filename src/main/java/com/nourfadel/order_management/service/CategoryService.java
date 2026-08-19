package com.nourfadel.order_management.service;

import com.nourfadel.order_management.Repository.CategoryRepository;
import com.nourfadel.order_management.dto.request.CategoryRequest;
import com.nourfadel.order_management.dto.response.CategoryResponse;
import com.nourfadel.order_management.entity.Category;
import com.nourfadel.order_management.exception.CategoryNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // create category
    public CategoryResponse create(CategoryRequest request) throws BadRequestException {
        Category parent = resolveParent(request.getParentCategoryId(),null);

        Category category = Category.builder()
                .name(request.getName())
                .parentCategoryId(parent)
                .build();

        return toResponse(category);
    }

    private CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategoryId(category.getParentCategoryId() != null ? category.getParentCategoryId().getId() : null)
                .build();
    }

    private Category resolveParent(Long parentId,Long selfId) throws BadRequestException {
        if (parentId == null){
            return null;
        }

        if (parentId.equals(selfId)){
            throw new BadRequestException("A category cannot be its own parent");
        }

        return categoryRepository.findById(parentId)
                .orElseThrow(() -> new CategoryNotFoundException("Parent category not found: "+ parentId));
    }

    // retrieve list of categories
    public List<CategoryResponse> findAll(){
        return categoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    // retrieve specific category
    public Category findById(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    // update category
    public CategoryResponse update(Long id,CategoryRequest request) throws BadRequestException {
        Category category = findById(id);

        Category parent = resolveParent(request.getParentCategoryId(),id);

        category.setName(request.getName());
        category.setParentCategoryId(parent);

        return toResponse(categoryRepository.save(category));
    }

    // delete category
    public void delete(Long categoryId) throws BadRequestException {
        Category category = findById(categoryId);
        if (!category.getCategories().isEmpty()){
            throw new BadRequestException("Cannot delete category id " + categoryId + ": it has child categories");
        }

        if (!category.getProducts().isEmpty()) {
            throw new BadRequestException("Cannot delete category id " + categoryId + ": it is still assigned to products");
        }
        categoryRepository.delete(category);
    }

}























