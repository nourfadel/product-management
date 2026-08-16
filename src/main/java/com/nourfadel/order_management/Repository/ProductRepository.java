package com.nourfadel.order_management.Repository;

import com.nourfadel.order_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
