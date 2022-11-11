package com.pokemonurpg.contest.input;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContestMoveTypeInputDtoTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;

    @Test
    public void testPojo() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setDescription(DESCRIPTION);
        input.setName(NAME);
        input.setScore(SCORE);
        input.setJam(JAM);

        assertEquals(DESCRIPTION, input.getDescription());
        assertEquals(NAME, input.getName());
        assertEquals(SCORE, input.getScore());
        assertEquals(JAM, input.getJam());
    }

}