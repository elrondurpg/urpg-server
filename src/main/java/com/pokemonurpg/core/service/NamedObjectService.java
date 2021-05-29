package com.pokemonurpg.core.service;

import com.pokemonurpg.core.model.NamedObject;

public interface NamedObjectService<T extends NamedObject> {
    T findByName(String name);
}
