package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.constants.ErrorConstants;
import com.pokemonurpg.v2.lib.enums.ActionEnum;
import com.pokemonurpg.v2.lib.enums.ResourceEnum;
import com.pokemonurpg.v2.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.lib.exception.NotFoundException;
import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.lib.validator.Validator;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundary;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UpdateTypeHandler implements UpdateTypeInputBoundary {
    private Types entities;
    private AuthorizationInputBoundary sessions;

    public UpdateTypeResponse update(int dbid, UpdateTypeRequest request) {
        if (sessions.authorize(ActionEnum.UPDATE, ResourceEnum.TYPE)) {
            Type model = entities.getByDbid(dbid);
            if (model != null) {
                updateModelFromRequest(model, request);
                Validator<Type> validator = entities.getValidator();
                if (validator.isValid(model)) {
                    return createResponse(entities.save(model));
                } else {
                    throw new ConstraintViolationException(validator.getErrors());
                }
            } else {
                throw new NotFoundException(String.format(ErrorConstants.NOT_FOUND_FORMATTABLE_MESSAGE, Type.class, "dbid", dbid));
            }
        }
        else {
            throw new UnauthorizedException();
        }
    }

    private void updateModelFromRequest(Type model, UpdateTypeRequest request) {
        if (request.getName() != null) {
            model.setName(request.getName());
        }
    }

    private UpdateTypeResponse createResponse(Type type) {
        return UpdateTypeResponse.builder()
                .dbid(type.getDbid())
                .name(type.getName())
                .build();
    }
}
