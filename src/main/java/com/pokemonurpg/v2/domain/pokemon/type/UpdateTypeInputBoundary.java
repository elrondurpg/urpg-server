package com.pokemonurpg.v2.domain.pokemon.type;

public interface UpdateTypeInputBoundary {
    UpdateTypeResponse handle(int dbid, UpdateTypeRequest request);
}
