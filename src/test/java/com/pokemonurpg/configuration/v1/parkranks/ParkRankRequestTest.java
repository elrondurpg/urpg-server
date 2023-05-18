package com.pokemonurpg.configuration.v1.parkranks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkRankRequestTest {
    private final static String NAME = "TEST";
    private final static String REQUIREMENT = "TESTREQ";

    @Test
    public void testPojo() {
        ParkRankRequest input = new ParkRankRequest();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);
        assertEquals(NAME, input.getName());
        assertEquals(REQUIREMENT, input.getRequirement());
    }
}