package com.pokemonurpg.contest.models;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ORASContestMoveTypeTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;

    @Test
    public void testConstructor() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setDescription(DESCRIPTION);
        input.setName(NAME);
        input.setScore(SCORE);
        input.setJam(JAM);

        ORASContestMoveType contestMoveType = new ORASContestMoveType(input);
        assertEquals(DESCRIPTION, contestMoveType.getDescription());
        assertEquals(NAME, contestMoveType.getName());
        assertEquals(SCORE, contestMoveType.getScore());
        assertEquals(JAM, contestMoveType.getJam());
    }
}