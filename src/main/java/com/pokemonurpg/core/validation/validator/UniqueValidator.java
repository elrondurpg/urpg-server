package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.validation.annotation.UniqueId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class UniqueValidator<T> implements ConstraintValidator<UniqueId, T> {
    @Override
    public abstract void initialize(UniqueId constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}