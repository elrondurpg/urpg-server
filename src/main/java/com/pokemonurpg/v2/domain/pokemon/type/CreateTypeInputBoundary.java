package com.pokemonurpg.v2.domain.pokemon.type;

public interface CreateTypeInputBoundary {
    CreateTypeResponse handle(CreateTypeRequest request);
}
