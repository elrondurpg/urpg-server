package com.pokemonurpg.core.validation.annotation;

import com.pokemonurpg.core.validation.ValidationService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.ws.rs.BadRequestException;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_00000;
import static com.pokemonurpg.strings.ErrorStrings.ERROR_00001;

@Aspect
@Configuration
public class ValidatedAspect {
    @Resource
    private ValidationService validationService;

    @Before("@annotation(com.pokemonurpg.core.validation.annotation.Validated) && args(input)")
    public void before(Object input) throws IllegalStateException {
        try {
            if (!validationService.validate(input)) {
                throw new BadRequestException(ERROR_00000);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(ERROR_00001);
        }
    }
}
