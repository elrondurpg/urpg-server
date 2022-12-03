package com.pokemonurpg.configuration.v3.shared.response;

import java.util.List;

public interface PagedResponse<T> {
    List<T> getContent();
    long getTotalElements();
    int getNumber();
    int getTotalPages();
    int getSize();
}
