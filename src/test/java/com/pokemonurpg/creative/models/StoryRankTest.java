package com.pokemonurpg.creative.models;

import com.pokemonurpg.configuration.v1.storyranks.StoryRankInputDto;
import com.pokemonurpg.entities.StoryRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoryRankTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;
    private final static String REQUIREMENT = "TEST";

    @Test
    public void testPojo() {
        StoryRank storyRank = new StoryRank();
        storyRank.setDbid(DBID);
        storyRank.setName(NAME);
        storyRank.setRequirement(REQUIREMENT);

        assertEquals(DBID, storyRank.getDbid());
        assertEquals(NAME, storyRank.getName());
        assertEquals(REQUIREMENT, storyRank.getRequirement());
    }

    @Test
    public void testConstructor() {
        StoryRankInputDto input = new StoryRankInputDto();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);

        StoryRank storyRank = new StoryRank(input);

        assertEquals(NAME, storyRank.getName());
        assertEquals(REQUIREMENT, storyRank.getRequirement());
    }
}