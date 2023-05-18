package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;
import com.pokemonurpg.entities.v1.RSEContestMoveType;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSEContestMoveTypeTest {
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

        RSEContestMoveType contestMoveType = new RSEContestMoveType(input);
        assertEquals(DESCRIPTION, contestMoveType.getDescription());
        assertEquals(NAME, contestMoveType.getName());
        assertEquals(SCORE, contestMoveType.getScore());
        assertEquals(JAM, contestMoveType.getJam());
    }
}