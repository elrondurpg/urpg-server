package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.enums.ActionEnum;
import com.pokemonurpg.v2.lib.enums.ResourceEnum;
import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundary;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class DeleteTypeHandler implements DeleteTypeInputBoundary {
    private Types entities;
    private AuthorizationInputBoundary sessions;

    @Override
    public DeleteTypeResponse deleteByDbid(int dbid) {
        if (sessions.authorize(ActionEnum.DELETE, ResourceEnum.TYPE)) {
            Type entity = entities.deleteByDbid(dbid);
            if (entity != null) {
                return buildResponse(entity);
            } else {
                return null;
            }
        }
        else {
            throw new UnauthorizedException();
        }
    }

    private DeleteTypeResponse buildResponse(Type entity) {
        return DeleteTypeResponse.builder()
                .dbid(entity.getDbid())
                .name(entity.getName())
                .build();
    }
}
