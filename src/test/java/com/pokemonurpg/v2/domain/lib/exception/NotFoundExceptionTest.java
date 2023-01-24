package com.pokemonurpg.v2.domain.lib.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    private final static String MESSAGE = "MESSAGE";

    @Test
    public void construct() {
        NotFoundException exception = new NotFoundException(MESSAGE);
        assertEquals(MESSAGE, exception.getMessage());
    }

}