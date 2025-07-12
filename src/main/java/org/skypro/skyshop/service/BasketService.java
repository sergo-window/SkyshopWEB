package org.skypro.skyshop.service;


import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.dto.BasketItem;
import org.skypro.skyshop.model.dto.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(() -> new NoSuchProductException("Товар с ID " + id + " не найден"));

        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getAllProducts();

        List<BasketItem> basketItems = basketMap.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(() -> new NoSuchProductException("Товар не найден на складе: " + entry.getKey()));
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        double total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getProductCost() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}
