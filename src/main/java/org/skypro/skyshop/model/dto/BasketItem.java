package org.skypro.skyshop.model.dto;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

public class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "Продукт не может быть null");
        if (quantity <= 0) {
            throw new NoSuchProductException("Количество должно быть положительным");
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getItemTotal() {
        return product.getProductCost() * quantity;
    }
}