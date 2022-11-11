package com.pokemonurpg.core.aspect;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

public class OutputValidationAspectTest {
    private OutputValidationAspect aspect = new OutputValidationAspect();

    @Test
    public void validateSuccessfulOutput() {
        aspect.validateOutput(new Object());
    }

    @Test
    public void throwNotFoundWhenOutputIsNull() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
            aspect.validateOutput(null));
    }
}