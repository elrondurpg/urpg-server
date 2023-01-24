package com.pokemonurpg.v2.domain.lib.validator;

import com.pokemonurpg.v2.entities.Entity;
import java.util.List;

public interface Validator<EntityType extends Entity> {
    boolean isValid(EntityType input);
    List<String> getErrors();
}
