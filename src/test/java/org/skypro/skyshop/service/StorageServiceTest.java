package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceTest {

    private final StorageService storageService = new StorageService();

    @Test
    void getAllSearchables_ShouldReturnCombinedCollection() {

        Collection<Searchable> result = storageService.getAllSearchables();

        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(item -> item instanceof Product));
        assertTrue(result.stream().anyMatch(item -> item instanceof Article));
    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        UUID productId = storageService.getProducts().iterator().next().getId();

        var result = storageService.getProductById(productId);

        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getId());
    }

    @Test
    void getProductById_WhenProductNotExists_ShouldReturnEmpty() {
        UUID nonExistentId = UUID.randomUUID();

        var result = storageService.getProductById(nonExistentId);

        assertTrue(result.isEmpty());
    }
}