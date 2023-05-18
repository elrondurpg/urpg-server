package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.lib.v1.models.NamedObject;

public interface NamedObjectService<T extends NamedObject> {
    T findByName(String name);
    T findByNameExact(String name);
}
