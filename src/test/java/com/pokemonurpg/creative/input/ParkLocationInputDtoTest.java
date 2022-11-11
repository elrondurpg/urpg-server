package com.pokemonurpg.creative.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkLocationInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ParkLocationInputDto input = new ParkLocationInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}