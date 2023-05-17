package com.pokemonurpg.lib.validation.annotation;

import com.pokemonurpg.lib.validation.validator.ExistsInDbIntValidator;
import com.pokemonurpg.lib.validation.validator.ExistsInDbStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExistsInDbStringValidator.class, ExistsInDbIntValidator.class })
@Documented
public @interface ExistsInDb {

    String message() default "The provided index does not belong to an existing object.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public Class type();

}