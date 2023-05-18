package com.pokemonurpg.lib.v1.services;

public interface IndexedObjectService<T> {
    T findByDbid(Integer dbid);
}
