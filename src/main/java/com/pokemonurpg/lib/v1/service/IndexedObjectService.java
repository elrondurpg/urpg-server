package com.pokemonurpg.lib.v1.service;

public interface IndexedObjectService<T> {
    T findByDbid(Integer dbid);
}
