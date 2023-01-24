package com.pokemonurpg.v2.lib.exception;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintViolationExceptionTest {
    private final static String ERROR = "ERROR";

    @Test
    public void construct_fromString() {
        ConstraintViolationException exception = new ConstraintViolationException(ERROR);
        assertEquals(1, exception.getErrors().size());
        assertTrue(exception.getErrors().contains(ERROR));
    }

    @Test
    public void construct_fromList() {
        List<String> list = Collections.singletonList(ERROR);
        ConstraintViolationException exception = new ConstraintViolationException(list);
        assertEquals(list, exception.getErrors());
    }
}