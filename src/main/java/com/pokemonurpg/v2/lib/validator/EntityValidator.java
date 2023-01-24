package com.pokemonurpg.v2.lib.validator;

import com.pokemonurpg.v2.entities.Entity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class EntityValidator<EntityType extends Entity> implements Validator<EntityType> {
    protected List<String> errors = new ArrayList<>();
}
