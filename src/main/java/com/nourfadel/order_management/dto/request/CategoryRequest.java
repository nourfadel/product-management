package com.nourfadel.order_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CategoryRequest {

    @NotBlank(message = "name is required")
    private String name;
    private Long parentCategoryId;

    public CategoryRequest() {
    }

    public CategoryRequest(String name, Long parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public @NotBlank(message = "name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name is required") String name) {
        this.name = name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
