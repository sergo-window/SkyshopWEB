package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void search_WhenStorageEmpty_ShouldReturnEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> result = searchService.search("молоко");

        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenNoMatches_ShouldReturnEmptyList() {
        Searchable product = new SimpleProduct("Хлеб", 50);
        Searchable article = new Article("Польза чая", "Чай содержит антиоксиданты...");

        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(product, article));
        Collection<SearchResult> result = searchService.search("молоко");

        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenMatchExists_ShouldReturnResults() {
        Searchable milkProduct = new SimpleProduct("Молоко", 80);
        Searchable breadProduct = new SimpleProduct("Хлеб", 50);
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(milkProduct, breadProduct));

        Collection<SearchResult> result = searchService.search("молок");

        assertEquals(1, result.size());
        SearchResult searchResult = result.iterator().next();
        assertEquals("Молоко", searchResult.getSearchableObjectName());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void search_WhenEmptyQuery_ShouldThrowException() {

        assertThrows(NoSuchProductException.class, () -> searchService.search(""));
        assertThrows(NoSuchProductException.class, () -> searchService.search("   "));
        assertThrows(NoSuchProductException.class, () -> searchService.search(null));
    }

    @Test
    void search_WhenPartialMatch_ShouldReturnResults() {
        Searchable milkProduct = new SimpleProduct("Молоко", 80);
        Searchable milkArticle = new Article("Польза молока", "Молоко полезно...");
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(milkProduct, milkArticle));

        Collection<SearchResult> result = searchService.search("мол");

        assertEquals(2, result.size());
        verify(storageService, times(1)).getAllSearchables();
    }
}
