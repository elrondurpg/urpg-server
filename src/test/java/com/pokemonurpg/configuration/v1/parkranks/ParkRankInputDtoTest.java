package com.pokemonurpg.configuration.v1.parkranks;

import com.pokemonurpg.configuration.v1.parkranks.ParkRankInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkRankInputDtoTest {
    private final static String NAME = "TEST";
    private final static String REQUIREMENT = "TESTREQ";

    @Test
    public void testPojo() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);
        assertEquals(NAME, input.getName());
        assertEquals(REQUIREMENT, input.getRequirement());
    }
}