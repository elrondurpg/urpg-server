package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.artranks.ArtRankInputDto;
import com.pokemonurpg.entities.v1.ArtRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtRankTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;
    private final static String REQUIREMENT = "TEST";

    @Test
    public void testPojo() {
        ArtRank artRank = new ArtRank();
        artRank.setDbid(DBID);
        artRank.setName(NAME);
        artRank.setRequirement(REQUIREMENT);

        assertEquals(DBID, artRank.getDbid());
        assertEquals(NAME, artRank.getName());
        assertEquals(REQUIREMENT, artRank.getRequirement());
    }

    @Test
    public void testConstructor() {
        ArtRankInputDto input = new ArtRankInputDto();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);

        ArtRank artRank = new ArtRank(input);

        assertEquals(NAME, artRank.getName());
        assertEquals(REQUIREMENT, artRank.getRequirement());
    }
}