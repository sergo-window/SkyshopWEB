package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final StorageService storageService;

    public ProductController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticles();
    }
}
