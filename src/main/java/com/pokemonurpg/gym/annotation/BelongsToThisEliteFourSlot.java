package com.pokemonurpg.gym.annotation;

import com.pokemonurpg.gym.validation.BelongsToThisEliteFourSlotValidator;
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
@Constraint(validatedBy = BelongsToThisEliteFourSlotValidator.class)
@Documented
public @interface BelongsToThisEliteFourSlot {

    String message() default "The ownership record provided in this request does not exist or does not belong to this Elite Four slot.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
