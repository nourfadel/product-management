package com.nourfadel.order_management.dto.response;

import com.nourfadel.order_management.entity.Category;

import java.util.List;

public class ProductResponse {

    private Long id;
    private String name;
    private Long price;
    private List<Long> categories;

    public ProductResponse() {
    }

    public ProductResponse(String name, Long price, List<Long> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
