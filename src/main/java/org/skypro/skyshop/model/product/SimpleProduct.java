package org.skypro.skyshop.model.product;


import org.skypro.skyshop.exceptions.NoSuchProductException;

public class SimpleProduct extends Product {
    private final int productCost;

    public SimpleProduct(String productName, int productCost) {
        super(productName);
        if (productCost <= 0) {
            throw new NoSuchProductException("\nОШИБКА! (некорректная цена продукта)");
        }
        this.productCost = productCost;
    }

    @Override
    public int getProductCost() {
        return productCost;
    }

    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getDisplayInfo() {
        return getProductName() + ": " + getProductCost() + " руб.";
    }

    @Override
    public String getSearchTerm() {
        return getStringRepresentation();
    }

    @Override
    public String getSearchableObjectType() {
        return "PRODUCT";
    }

    @Override
    public String getSearchableObjectName() {
        return getProductName();
    }
}
