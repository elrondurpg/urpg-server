package com.pokemonurpg.security.annotation;

import com.pokemonurpg.core.validation.validator.UniqueNameValidator;
import com.pokemonurpg.security.validator.ValidStarterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidStarterValidator.class)
@Documented
public @interface ValidStarter {

    String message() default "The provided starter Pokemon was invalid.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}