package com.pokemonurpg.configuration.v1.parklocations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkLocationRequestTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ParkLocationRequest input = new ParkLocationRequest();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}