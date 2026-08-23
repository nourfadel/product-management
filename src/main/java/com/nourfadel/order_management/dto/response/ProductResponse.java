package com.nourfadel.order_management.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private Long price;
    private Set<Long> categories;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, Long price, Set<Long> categories) {
        this.id = id;
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

    public Set<Long> getCategories() {
        return categories;
    }

    public void setCategories(Set<Long> categories) {
        this.categories = categories;
    }
}
