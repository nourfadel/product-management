package com.nourfadel.order_management.dto.request;

import com.nourfadel.order_management.entity.Order;
import com.nourfadel.order_management.entity.OrderItem;
import com.nourfadel.order_management.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    private OrderStatus status;

    @Valid
    @NotEmpty(message = "items must contain at least one item!")
    private List<OrderItemRequest> items;

    public OrderRequest() {
    }

    public OrderRequest(Long userId, OrderStatus status, List<OrderItemRequest> items) {
        this.userId = userId;
        this.status = status;
        this.items = items;
    }

    public @NotNull(message = "userId is required") Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "userId is required") Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public @Valid @NotEmpty(message = "items must contain at least one item!") List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(@Valid @NotEmpty(message = "items must contain at least one item!") List<OrderItemRequest> items) {
        this.items = items;
    }
}
