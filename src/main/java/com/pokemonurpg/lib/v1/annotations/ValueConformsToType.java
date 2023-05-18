package com.pokemonurpg.lib.v1.annotations;

import com.pokemonurpg.lib.v1.validators.ValueConformsToTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValueConformsToTypeValidator.class)
@Documented
public @interface ValueConformsToType {

    String message() default "The provided value does not conform to the provided or known type.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}