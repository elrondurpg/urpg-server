package com.pokemonurpg.configuration.v1.contestattributes;

import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestAttributeInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestAttributeInputDto input = new ContestAttributeInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}