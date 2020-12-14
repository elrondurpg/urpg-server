package com.pokemonurpg.core.service;

public interface NamedObjectService<T> {
    T findByName(String name);
}
