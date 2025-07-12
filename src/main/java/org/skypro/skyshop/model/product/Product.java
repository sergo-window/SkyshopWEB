package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String productName;

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new NoSuchProductException("\nОШИБКА! (некорректное название продукта)");
        }
        this.id = UUID.randomUUID();
        this.productName = productName;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductCost();

    public abstract boolean isSpecial();

    public abstract String getDisplayInfo();

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public String getSearchableObjectType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return "Товар: '" + productName + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(getSearchableObjectName(), ((Searchable) o).getSearchableObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSearchableObjectName());
    }
}