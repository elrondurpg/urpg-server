package com.pokemonurpg.configuration.v1.lib.service;

@FunctionalInterface
public interface FieldSetter<T> {
    void setValue(T value);
}
