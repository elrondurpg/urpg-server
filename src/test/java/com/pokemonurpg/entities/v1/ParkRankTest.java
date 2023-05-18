package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.parkranks.ParkRankRequest;
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
        ParkRankRequest input = new ParkRankRequest();
        input.setName(NAME);
        input.setRequirement(REQUIREMENT);

        ParkRank parkRank = new ParkRank(input);

        assertEquals(NAME, parkRank.getName());
        assertEquals(REQUIREMENT, parkRank.getRequirement());
    }
}