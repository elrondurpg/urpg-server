package com.pokemonurpg.configuration.v1.artranks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtRankRequestTest {
    private final static String NAME = "TEST";
    private final static String REQUIREMENT = "TESTREQ";

    @Test
    public void testPojo() {
        ArtRankRequest input = new ArtRankRequest();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);
        assertEquals(NAME, input.getName());
        assertEquals(REQUIREMENT, input.getRequirement());
    }
}