package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.lib.enums.ActionEnum;
import com.pokemonurpg.v2.lib.enums.ResourceEnum;
import com.pokemonurpg.v2.lib.exception.ConstraintViolationException;
import com.pokemonurpg.v2.lib.exception.UnauthorizedException;
import com.pokemonurpg.v2.lib.validator.Validator;
import com.pokemonurpg.v2.domain.member.session.AuthorizationInputBoundary;
import com.pokemonurpg.v2.entities.pokemon.Type;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CreateTypeHandler implements CreateTypeInputBoundary {
    private Types entities;
    private AuthorizationInputBoundary sessions;

    public CreateTypeResponse create(CreateTypeRequest request) {
        if (sessions.authorize(ActionEnum.CREATE, ResourceEnum.TYPE)) {
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
        else {
            throw new UnauthorizedException();
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
