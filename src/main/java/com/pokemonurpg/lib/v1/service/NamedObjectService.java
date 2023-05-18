package com.pokemonurpg.lib.v1.service;

import com.pokemonurpg.lib.v1.model.NamedObject;

public interface NamedObjectService<T extends NamedObject> {
    T findByName(String name);
    T findByNameExact(String name);
}
