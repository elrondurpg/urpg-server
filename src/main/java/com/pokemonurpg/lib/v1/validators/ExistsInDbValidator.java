package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class ExistsInDbValidator<T> implements ConstraintValidator<ExistsInDb, T> {
    @Override
    public abstract void initialize(ExistsInDb constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}
