package com.pokemonurpg.configuration.v1.types;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeRequestTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        TypeRequest input = new TypeRequest();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}