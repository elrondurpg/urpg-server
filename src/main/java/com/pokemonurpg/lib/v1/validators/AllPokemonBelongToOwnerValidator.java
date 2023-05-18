package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.AllPokemonBelongToOwner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class AllPokemonBelongToOwnerValidator<T> implements ConstraintValidator<AllPokemonBelongToOwner, T> {
    @Override
    public abstract void initialize(AllPokemonBelongToOwner constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}