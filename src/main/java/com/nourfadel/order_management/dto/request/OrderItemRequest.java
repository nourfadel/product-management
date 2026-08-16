package com.nourfadel.order_management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be at least 1")
    private Long quantity;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public @NotNull(message = "productId is required") Long getProductId() {
        return productId;
    }

    public void setProductId(@NotNull(message = "productId is required") Long productId) {
        this.productId = productId;
    }

    public @NotNull(message = "quantity is required") @Min(value = 1, message = "quantity must be at least 1") Long getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "quantity is required") @Min(value = 1, message = "quantity must be at least 1") Long quantity) {
        this.quantity = quantity;
    }
}
