package com.pokemonurpg.contest.input;

import com.pokemonurpg.contest.input.ContestRankInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContestRankInputDtoTest {
    private final static String NAME = "TEST";

    @Test
    public void testPojo() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);
        assertEquals(NAME, input.getName());
    }
}