package com.pokemonurpg.creative.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkLocationInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}