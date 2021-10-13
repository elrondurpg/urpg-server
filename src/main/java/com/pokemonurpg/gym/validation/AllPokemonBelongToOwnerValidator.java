package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.gym.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.gym.input.GymPokemonInputDto;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymOwnershipTerm;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public abstract class AllPokemonBelongToOwnerValidator<T> implements ConstraintValidator<AllPokemonBelongToOwner, T> {
    @Override
    public abstract void initialize(AllPokemonBelongToOwner constraintAnnotation);
    @Override
    public abstract boolean isValid(T t, ConstraintValidatorContext constraintValidatorContext);
}