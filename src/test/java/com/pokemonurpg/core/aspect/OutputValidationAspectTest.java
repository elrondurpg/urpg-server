package com.pokemonurpg.core.aspect;

import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;

public class OutputValidationAspectTest {
    private OutputValidationAspect aspect = new OutputValidationAspect();

    @Test
    public void validateSuccessfulOutput() {
        aspect.validateOutput(new Object());
    }

    @Test(expected = ResponseStatusException.class)
    public void throwNotFoundWhenOutputIsNull() {
        aspect.validateOutput(null);
    }
}