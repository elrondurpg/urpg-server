package com.pokemonurpg.lib.v1.annotations;

import com.pokemonurpg.lib.v1.validators.UniqueChampionRecordIdValidator;
import com.pokemonurpg.lib.v1.validators.UniqueEliteFourRecordIdValidator;
import com.pokemonurpg.lib.v1.validators.UniqueGymLeaderRecordIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueGymLeaderRecordIdValidator.class, UniqueEliteFourRecordIdValidator.class, UniqueChampionRecordIdValidator.class })
@Documented
public @interface UniqueId {

    String message() default "The provided object does not have a unique ID.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}