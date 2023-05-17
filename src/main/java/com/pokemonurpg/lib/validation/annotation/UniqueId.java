package com.pokemonurpg.lib.validation.annotation;

import com.pokemonurpg.lib.validation.validator.UniqueChampionOwnershipTermIdValidator;
import com.pokemonurpg.lib.validation.validator.UniqueEliteFourOwnershipTermIdValidator;
import com.pokemonurpg.lib.validation.validator.UniqueGymOwnershipTermIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueGymOwnershipTermIdValidator.class, UniqueEliteFourOwnershipTermIdValidator.class, UniqueChampionOwnershipTermIdValidator.class })
@Documented
public @interface UniqueId {

    String message() default "The provided object does not have a unique ID.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}