package com.nourfadel.order_management.dto.request;

import com.nourfadel.order_management.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Set;

public class ProductRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Long price;

    @NotNull(message = "categories must be provided (use an empty list if none)")
    private List<Long> categories;

    public ProductRequest() {
    }

    public ProductRequest(String name, Long price, List<Long> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public @NotBlank(message = "name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "price is required") @Positive(message = "price must be positive") Long getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "price is required") @Positive(message = "price must be positive") Long price) {
        this.price = price;
    }

    public @NotNull(message = "categories must be provided (use an empty list if none)") List<Long> getCategories() {
        return categories;
    }

    public void setCategories(@NotNull(message = "categories must be provided (use an empty list if none)") List<Long> categories) {
        this.categories = categories;
    }
}
