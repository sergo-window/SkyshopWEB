package org.skypro.skyshop.model.basket;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;


@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productsMap;

    public ProductBasket() {
        this.productsMap = new HashMap<>();
    }

    public void addProduct(UUID productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }
        productsMap.merge(productId, 1, Integer::sum);
    }

    public Map<UUID, Integer> getAllProducts() {
        return Map.copyOf(productsMap);
    }
}