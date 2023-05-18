package com.pokemonurpg.configuration.v1.storyranks;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoryRankRequestTest {
    private final static String NAME = "TEST";
    private final static String REQUIREMENT = "TESTREQ";

    @Test
    public void testPojo() {
        StoryRankRequest input = new StoryRankRequest();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);
        assertEquals(NAME, input.getName());
        assertEquals(REQUIREMENT, input.getRequirement());
    }
}