package com.pokemonurpg.configuration.v1.gym.lib.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pokemonurpg.configuration.v1.gym.lib.annotation.AllPokemonBelongToOwner;

public abstract class AllPokemonBelongToOwnerValidator<T> implements ConstraintValidator<AllPokemonBelongToOwner, T> {
    @Override
    public abstract void initialize(AllPokemonBelongToOwner constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}