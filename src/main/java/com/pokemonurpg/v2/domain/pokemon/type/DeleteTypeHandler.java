package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class DeleteTypeHandler implements DeleteTypeInputBoundary {
    private Types entities;

    @Override
    public DeleteTypeResponse deleteByDbid(int dbid) {
        Type entity = entities.deleteByDbid(dbid);
        if (entity != null) {
            return buildResponse(entity);
        }
        else {
            return null;
        }
    }

    private DeleteTypeResponse buildResponse(Type entity) {
        return DeleteTypeResponse.builder()
                .dbid(entity.getDbid())
                .name(entity.getName())
                .build();
    }
}
