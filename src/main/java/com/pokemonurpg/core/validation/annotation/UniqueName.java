package com.pokemonurpg.core.validation.annotation;

import com.pokemonurpg.core.validation.validator.UniqueNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
@Documented
public @interface UniqueName {

    String message() default "The provided name was not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public Class type();
}