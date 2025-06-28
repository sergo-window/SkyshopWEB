package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String articleName;
    private final String articleText;

    public Article(String articleName, String articleText) {
        this.id = UUID.randomUUID();
        this.articleName = articleName;
        this.articleText = articleText;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return articleName + " " + articleText;
    }

    @Override
    public String getSearchableObjectType() {
        return "ARTICLE";
    }

    @Override
    public String getSearchableObjectName() {
        return articleName;
    }

    @Override
    public String toString() {
        return "Статья: '" + articleName + "'";
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