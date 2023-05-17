package com.pokemonurpg.lib.validation.annotation;

import com.pokemonurpg.lib.validation.validator.UniqueDiscordIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueDiscordIdValidator.class)
@Documented
public @interface UniqueDiscordId {

    String message() default "The provided Discord ID was not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}