package com.pokemonurpg.configuration.v1.contesteffects;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContestEffectInputDtoTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;

    @Test
    public void testPojo() {
        ContestEffectRequest input = new ContestEffectRequest();
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