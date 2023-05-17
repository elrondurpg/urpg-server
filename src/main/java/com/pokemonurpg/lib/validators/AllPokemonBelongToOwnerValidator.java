package com.pokemonurpg.lib.validators;

import com.pokemonurpg.lib.annotations.AllPokemonBelongToOwner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class AllPokemonBelongToOwnerValidator<T> implements ConstraintValidator<AllPokemonBelongToOwner, T> {
    @Override
    public abstract void initialize(AllPokemonBelongToOwner constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}