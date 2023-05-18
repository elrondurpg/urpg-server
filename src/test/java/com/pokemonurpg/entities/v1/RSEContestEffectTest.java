package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class RSEContestEffectTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;

    @Test
    public void testConstructor() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setDescription(DESCRIPTION);
        input.setName(NAME);
        input.setScore(SCORE);
        input.setJam(JAM);

        RSEContestEffect contestMoveType = new RSEContestEffect(input);
        assertEquals(DESCRIPTION, contestMoveType.getDescription());
        assertEquals(NAME, contestMoveType.getName());
        assertEquals(SCORE, contestMoveType.getScore());
        assertEquals(JAM, contestMoveType.getJam());
    }
}