package com.pokemonurpg.core.service;

public interface IndexedObjectService<T> {
    T findByDbid(Integer dbid);
}
