package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;

public interface DeleteTypeInputBoundary {
    DeleteTypeResponse deleteByDbid(int dbid);
}
