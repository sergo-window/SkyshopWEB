package org.skypro.skyshop.model.basket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductBasketTest {

    @InjectMocks
    private ProductBasket productBasket;

    @Test
    void addProduct_WhenProductIdIsNull_ShouldThrowException() {

        assertThrows(NoSuchProductException.class,
                () -> productBasket.addProduct(null),
                "Ожидалось исключение при null ID продукта"
        );
    }

    @Test
    void addProduct_WhenValidProductId_ShouldAddToMap() {

        UUID productId = UUID.randomUUID();

        productBasket.addProduct(productId);

        Map<UUID, Integer> products = productBasket.getAllProducts();
        assertEquals(1, products.size());
        assertEquals(1, products.get(productId));
    }

    @Test
    void addProduct_WhenExistingProduct_ShouldIncrementQuantity() {

        UUID productId = UUID.randomUUID();
        productBasket.addProduct(productId);

        productBasket.addProduct(productId);

        Map<UUID, Integer> products = productBasket.getAllProducts();
        assertEquals(1, products.size());
        assertEquals(2, products.get(productId));
    }

    @Test
    void getAllProducts_WhenBasketEmpty_ShouldReturnEmptyMap() {

        Map<UUID, Integer> result = productBasket.getAllProducts();

        assertTrue(result.isEmpty(), "Корзина должна быть пустой");
    }

    @Test
    void getAllProducts_WhenBasketHasProducts_ShouldReturnCorrectMap() {

        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        productBasket.addProduct(productId1);
        productBasket.addProduct(productId1);
        productBasket.addProduct(productId2);

        Map<UUID, Integer> result = productBasket.getAllProducts();

        assertEquals(2, result.size());
        assertEquals(2, result.get(productId1));
        assertEquals(1, result.get(productId2));
    }

    @Test
    void getAllProducts_ShouldReturnUnmodifiableMap() {

        UUID productId = UUID.randomUUID();
        productBasket.addProduct(productId);

        Map<UUID, Integer> result = productBasket.getAllProducts();

        assertThrows(UnsupportedOperationException.class,
                () -> result.put(UUID.randomUUID(), 1),
                "Ожидалось, что возвращаемая карта будет неизменяемой"
        );
    }
}
