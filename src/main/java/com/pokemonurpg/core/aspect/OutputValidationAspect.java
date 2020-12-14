package com.pokemonurpg.core.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Configuration
public class OutputValidationAspect {
    @AfterReturning(pointcut = "@annotation(org.springframework.web.bind.annotation.ResponseBody)", returning = "response")
    public void validateOutput(Object response) throws IllegalStateException {
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
