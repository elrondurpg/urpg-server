package com.pokemonurpg.configuration.v2.shared.view;

import java.util.List;

public interface ListView<T> {
    List<? extends T> getContent();
    long getTotalItems();
    int getPage();
    int getTotalPages();
    int getSize();
}
