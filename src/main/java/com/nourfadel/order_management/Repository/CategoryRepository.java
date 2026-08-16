package com.nourfadel.order_management.Repository;

import com.nourfadel.order_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
