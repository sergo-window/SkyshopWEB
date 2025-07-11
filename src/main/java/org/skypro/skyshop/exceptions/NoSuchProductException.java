package org.skypro.skyshop.exceptions;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(UUID productId) {
        super("Product not found with ID: " + productId);
    }

    public NoSuchProductException(String message) {
        super(message);
    }
}
