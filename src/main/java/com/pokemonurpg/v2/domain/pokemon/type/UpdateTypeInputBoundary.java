package com.pokemonurpg.v2.domain.pokemon.type;

public interface UpdateTypeInputBoundary {
    UpdateTypeResponse update(int dbid, UpdateTypeRequest request);
}
