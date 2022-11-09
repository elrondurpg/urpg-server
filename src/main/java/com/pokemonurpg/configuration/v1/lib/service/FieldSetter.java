package com.pokemonurpg.configuration.v1.lib.service;

import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;

@FunctionalInterface
public interface FieldSetter<T> {
    void setValue(T value);
}
