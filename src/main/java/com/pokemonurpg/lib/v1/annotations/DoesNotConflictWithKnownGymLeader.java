package com.pokemonurpg.lib.v1.annotations;

import com.pokemonurpg.lib.v1.validators.DoesNotConflictWithKnownGymLeaderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { DoesNotConflictWithKnownGymLeaderValidator.class })
@Documented
public @interface DoesNotConflictWithKnownGymLeader {

    String message() default "The provided name belongs to a known Gym Leader, Elite Four Member, or URPG Champion.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}