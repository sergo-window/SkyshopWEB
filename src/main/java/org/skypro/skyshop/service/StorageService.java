package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        createTestData();
    }

    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Article> getArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }

    private void createTestData() {
        products.put(new SimpleProduct("Молоко", 80).getId(), new SimpleProduct("Молоко", 80));
        products.put(new DiscountedProduct("Хлеб", 50, 10).getId(), new DiscountedProduct("Хлеб", 50, 10));
        products.put(new FixPriceProduct("Кофе").getId(), new FixPriceProduct("Кофе"));

        articles.put(new Article("Польза молока", "Молоко содержит кальций...").getId(),
                new Article("Польза молока", "Молоко содержит кальций..."));
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(getProducts());
        result.addAll(getArticles());
        return result;
    }
}
