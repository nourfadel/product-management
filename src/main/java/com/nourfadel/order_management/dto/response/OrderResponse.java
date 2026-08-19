package com.nourfadel.order_management.dto.response;

import com.nourfadel.order_management.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class OrderResponse {

    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private double totalAmount;
    private OrderStatus status;
    private List<OrderItemResponse> items;

    public OrderResponse() {
    }

    public OrderResponse(Long id, Long userId, LocalDateTime createdAt, double totalAmount, OrderStatus status, List<OrderItemResponse> items) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
