package com.pokemonurpg.v2.domain.pokemon.type;

import com.pokemonurpg.v2.entities.pokemon.Type;
import com.pokemonurpg.v2.lib.validator.EntityValidator;

public class TypeValidator extends EntityValidator<Type> {

    @Override
    public boolean isValid(Type input) {
        return true;
    }
}
