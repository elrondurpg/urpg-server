package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class ExistsInDbValidator<T> implements ConstraintValidator<ExistsInDb, T> {
    @Override
    public abstract void initialize(ExistsInDb constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}
