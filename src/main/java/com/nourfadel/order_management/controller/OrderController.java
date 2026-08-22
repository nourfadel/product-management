package com.nourfadel.order_management.controller;


import com.nourfadel.order_management.dto.request.OrderRequest;
import com.nourfadel.order_management.dto.response.OrderResponse;
import com.nourfadel.order_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // create product
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        OrderResponse created = orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // find product
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    // find all products
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }

    // update product
    @PutMapping("/{OrderId}")
    public ResponseEntity<OrderResponse> updateProduct(
            @PathVariable Long OrderId,
            @Valid @RequestBody OrderRequest request
    ){
        return ResponseEntity.ok(orderService.update(OrderId,request));
    }

    // delete product
    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long OrderId){
        orderService.delete(OrderId);
        return ResponseEntity.noContent().build();
    }
}
