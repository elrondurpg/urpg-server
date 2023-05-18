package com.pokemonurpg.configuration.v1.contestattributes;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContestAttributeRequestTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestAttributeRequest input = new ContestAttributeRequest();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}