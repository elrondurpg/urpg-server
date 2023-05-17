package com.pokemonurpg.lib.service;

import com.pokemonurpg.lib.model.NamedObject;

public interface NamedObjectService<T extends NamedObject> {
    T findByName(String name);
    T findByNameExact(String name);
}
