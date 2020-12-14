package com.pokemonurpg.creative.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoryRankInputDtoTest {
    private final static String NAME = "TEST";
    private final static String REQUIREMENT = "TESTREQ";

    @Test
    public void testPojo() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);
        assertEquals(NAME, input.getName());
        assertEquals(REQUIREMENT, input.getRequirement());
    }
}