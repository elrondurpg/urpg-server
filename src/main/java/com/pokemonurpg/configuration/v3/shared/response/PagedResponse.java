package com.pokemonurpg.configuration.v3.shared.response;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = PagedResponse.class)
public interface PagedResponse<T> {
    List<? extends T> getContent();
    long getTotalElements();
    int getNumber();
    int getTotalPages();
    int getSize();
}
