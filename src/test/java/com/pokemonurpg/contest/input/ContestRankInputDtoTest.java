package com.pokemonurpg.contest.input;

import com.pokemonurpg.configuration.v1.contestranks.ContestRankInputDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestRankInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}