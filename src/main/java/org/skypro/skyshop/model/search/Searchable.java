package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    UUID getId();
    String getSearchTerm();
    String getSearchableObjectType();
    String getSearchableObjectName();

    default String getStringRepresentation() {
        return String.format("%s [%s] (ID: %s)",
                getSearchableObjectName(),
                getSearchableObjectType(),
                getId());
    }
}