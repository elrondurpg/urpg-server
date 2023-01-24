package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.domain.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.domain.lib.validator.Validator;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateTypeHandler implements CreateTypeInputBoundary {
    private Types entities;

    public CreateTypeResponse handle(CreateTypeRequest request) {
        Type input = createType(request);

        Validator<Type> validator = entities.getValidator();
        if (validator.isValid(input)) {
            Type output = entities.save(input);
            return createResponse(output);
        }
        else {
            throw new ConstraintViolationException(validator.getErrors());
        }
    }

    private Type createType(CreateTypeRequest request) {
        return TypeModel.builder()
                .name(request.getName())
                .build();
    }

    private CreateTypeResponse createResponse(Type type) {
        return CreateTypeResponse.builder()
                .dbid(type.getDbid())
                .name(type.getName())
                .build();
    }
}
