package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.UniqueId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class UniqueValidator<T> implements ConstraintValidator<UniqueId, T> {
    @Override
    public abstract void initialize(UniqueId constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}
