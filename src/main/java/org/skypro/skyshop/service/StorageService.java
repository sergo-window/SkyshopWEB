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
        Product product = new SimpleProduct("Молоко", 80);
        products.put(product.getId(), product);
        Product product1 = new DiscountedProduct("Хлеб", 50, 10);
        products.put(product1.getId(), product1);
        Product product2 = new FixPriceProduct("Кофе");
        products.put(product2.getId(), product2);
        Product product3 = new DiscountedProduct("Коньяк", 750, 20);
        products.put(product3.getId(), product3);
        Product product4 = new SimpleProduct("Мясо", 400);
        products.put(product4.getId(), product4);

        Article article = new Article("Польза молока", "Молоко содержит кальций...");
        articles.put(article.getId(), article);
        Article article1 = new Article("Как выбрать чай", "Чай бывает разных сортов (черный, зеленый, красный...");
        articles.put(article1.getId(), article1);
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(getProducts());
        result.addAll(getArticles());
        return result;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
