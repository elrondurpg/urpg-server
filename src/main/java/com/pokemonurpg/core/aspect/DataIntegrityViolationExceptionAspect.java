package com.pokemonurpg.core.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_00000;

@Aspect
@Configuration
public class DataIntegrityViolationExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.pokemonurpg..*(..))", throwing = "error")
    public void afterThrowingDataIntegrityViolationException(DataIntegrityViolationException error) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_00000);
    }

}
