package com.pokemonurpg.lib.service;

public interface IndexedObjectService<T> {
    T findByDbid(Integer dbid);
}
