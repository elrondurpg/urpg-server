package com.pokemonurpg.creative.models;

import com.pokemonurpg.creative.input.ParkRankInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkRankTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;
    private final static String REQUIREMENT = "TEST";

    @Test
    public void testPojo() {
        ParkRank parkRank = new ParkRank();
        parkRank.setDbid(DBID);
        parkRank.setName(NAME);
        parkRank.setRequirement(REQUIREMENT);

        assertEquals(DBID, parkRank.getDbid());
        assertEquals(NAME, parkRank.getName());
        assertEquals(REQUIREMENT, parkRank.getRequirement());
    }

    @Test
    public void testConstructor() {
        ParkRankInputDto input = new ParkRankInputDto();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);

        ParkRank parkRank = new ParkRank(input);

        assertEquals(NAME, parkRank.getName());
        assertEquals(REQUIREMENT, parkRank.getRequirement());
    }
}