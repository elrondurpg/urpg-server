package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.constants.ErrorConstants;
import com.pokemonurpg.v2.domain.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.domain.lib.exception.NotFoundException;
import com.pokemonurpg.v2.domain.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UpdateTypeHandler {
    private Types entities;

    public UpdateTypeResponse handle(int dbid, UpdateTypeRequest request) {
        Type model = entities.getByDbid(dbid);
        if (model != null) {
            updateModelFromRequest(model, request);
            Validator<Type> validator = entities.getValidator();
            if (validator.isValid(model)) {
                return createResponse(entities.save(model));
            }
            else {
                throw new ConstraintViolationException(validator.getErrors());
            }
        }
        else {
            throw new NotFoundException(String.format(ErrorConstants.NOT_FOUND_FORMATTABLE_MESSAGE, Type.class, "dbid", dbid));
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
