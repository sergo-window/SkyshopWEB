package org.skypro.skyshop.model.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserBasket {
    private final List<BasketItem> basketItems;
    private final double total;

    public UserBasket(List<BasketItem> basketItems, double total) {
        this.basketItems = List.copyOf(Objects.requireNonNull(basketItems));
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        return basketItems.stream()
                .mapToDouble(BasketItem::getItemTotal)
                .sum();
    }

    public List<BasketItem> getBasketItems() {
        return Collections.unmodifiableList(basketItems);
    }

    public double getTotal() {
        return total;
    }
}