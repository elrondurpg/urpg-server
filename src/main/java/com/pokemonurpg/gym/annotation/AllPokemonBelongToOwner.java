package com.pokemonurpg.gym.annotation;

import com.pokemonurpg.gym.validation.AllPokemonBelongToChampionValidator;
import com.pokemonurpg.gym.validation.AllPokemonBelongToEliteFourMemberValidator;
import com.pokemonurpg.gym.validation.AllPokemonBelongToGymOwnerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { AllPokemonBelongToGymOwnerValidator.class, AllPokemonBelongToEliteFourMemberValidator.class, AllPokemonBelongToChampionValidator.class })
@Documented
public @interface AllPokemonBelongToOwner {

    String message() default "One or more Pokemon provided in this request do not belong to the owner.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
