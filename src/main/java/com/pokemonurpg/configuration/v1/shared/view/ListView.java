package com.pokemonurpg.configuration.v1.shared.view;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ListView.class)
public interface ListView<T> {
    List<T> getContent();
    long getTotalElements();
    int getNumber();
    int getTotalPages();
    int getSize();
}
