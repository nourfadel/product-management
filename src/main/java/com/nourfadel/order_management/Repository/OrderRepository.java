package com.nourfadel.order_management.Repository;

import com.nourfadel.order_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
