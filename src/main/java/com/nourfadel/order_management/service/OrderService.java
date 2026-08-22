package com.nourfadel.order_management.service;

import com.nourfadel.order_management.Repository.OrderRepository;
import com.nourfadel.order_management.Repository.ProductRepository;
import com.nourfadel.order_management.dto.request.OrderItemRequest;
import com.nourfadel.order_management.dto.request.OrderRequest;
import com.nourfadel.order_management.dto.response.OrderItemResponse;
import com.nourfadel.order_management.dto.response.OrderResponse;
import com.nourfadel.order_management.entity.Order;
import com.nourfadel.order_management.entity.OrderItem;
import com.nourfadel.order_management.entity.Product;
import com.nourfadel.order_management.enums.OrderStatus;
import com.nourfadel.order_management.exception.OrderNotFoundException;
import com.nourfadel.order_management.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // create order
    public OrderResponse create(OrderRequest request){
        Order order = Order.builder()
                .userId(request.getUserId())
                .orderStatus(request.getStatus() != null ? request.getStatus() : OrderStatus.PENDING)
                .build();

        applyItems(order, request.getItems());
        recalculateTotal(order);

        return toResponse(orderRepository.save(order));

    }

    private OrderResponse toResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(i -> OrderItemResponse.builder()
                        .id(i.getId())
                        .productId(i.getProduct().getId())
                        .productName(i.getProduct().getName())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .subTotal(i.getUnitPrice() * (Double.valueOf(i.getQuantity())))
                        .build())
                .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .status(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .totalAmount(order.getTotalAmount())
                .items(itemResponses)
                .build();
    }

    private void recalculateTotal(Order order) {
        Double total = order.getItems().stream()
                .map(i -> i.getUnitPrice() * (Double.valueOf(i.getQuantity())))
                .reduce(0.0, Double::sum);
        order.setTotalAmount(total);
    }


    private void applyItems(Order order, List<OrderItemRequest> itemRequests) {
        for (OrderItemRequest itemRequest : itemRequests) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product"+itemRequest.getProductId()));

            OrderItem item = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .unitPrice(product.getPrice())
                    .build();

            order.addItem(item);
        }
    }

    // get all orders
    public List<OrderResponse> findAll(){
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    // get order
    public OrderResponse findById(Long id){
        return toResponse(getOrThrow(id));
    }

    private Order getOrThrow(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"+id));
    }

    // update order
    public OrderResponse update(Long id,OrderRequest request){
        Order order = getOrThrow(id);

        if (request.getStatus() != null)
            order.setOrderStatus(request.getStatus());

        order.setUserId(request.getUserId());
        applyItems(order,request.getItems());
        recalculateTotal(order);

        return toResponse(order);
    }

    // delete order
    public void delete(Long id){
        Order order = getOrThrow(id);
        orderRepository.delete(order);
    }


}
