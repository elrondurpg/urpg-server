package com.pokemonurpg.contest.models;

import com.pokemonurpg.configuration.v1.contestranks.ContestRankInputDto;
import com.pokemonurpg.entities.ContestRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestRankTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2432;

    @Test
    public void testPojo() {
        ContestRank contestRank = new ContestRank();
        contestRank.setDbid(DBID);
        contestRank.setName(NAME);

        assertEquals(DBID, contestRank.getDbid());
        assertEquals(NAME, contestRank.getName());
    }

    @Test
    public void testConstructor() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);

        ContestRank contestRank = new ContestRank(input);

        assertEquals(NAME, contestRank.getName());
    }
}