package com.nourfadel.order_management.entity;

import com.nourfadel.order_management.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime createdAt;
    private double totalAmount;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Builder.Default
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, Long userId, LocalDateTime createdAt, double totalAmount, OrderStatus orderStatus, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.items = items;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}
