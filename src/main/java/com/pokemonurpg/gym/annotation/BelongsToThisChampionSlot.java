package com.pokemonurpg.gym.annotation;

import com.pokemonurpg.gym.validation.BelongsToThisGymValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = BelongsToThisGymValidator.class)
@Documented
public @interface BelongsToThisChampionSlot {

    String message() default "The ownership record provided in this request does not exist or does not belong to this champion slot.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
