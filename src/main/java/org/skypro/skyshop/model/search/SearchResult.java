package org.skypro.skyshop.model.search;

import java.util.Objects;

public final class SearchResult {

    private final String id;
    private final String searchableObjectName;
    private final String searchableObjectType;

    public SearchResult(String id, String searchableObjectName, String searchableObjectType) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.searchableObjectName = Objects.requireNonNull(searchableObjectName, "Name cannot be null");
        this.searchableObjectType = Objects.requireNonNull(searchableObjectType, "Type cannot be null");
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        Objects.requireNonNull(searchable, "Searchable cannot be null");
        return new SearchResult(
                searchable.getId().toString(),
                searchable.getSearchableObjectName(),
                searchable.getSearchableObjectType()
        );
    }

    public String getId() { return id; }
    public String getSearchableObjectName() { return searchableObjectName; }
    public String getSearchableObjectType() { return searchableObjectType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + searchableObjectName + '\'' +
                ", type='" + searchableObjectType + '\'' +
                '}';
    }
}
