package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestEffectTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;
    private final static Integer DBID = 32432;

    @Test
    public void testPojo() {
        ContestEffect contestEffect = new ContestEffect();
        contestEffect.setDbid(DBID);
        contestEffect.setDescription(DESCRIPTION);
        contestEffect.setName(NAME);
        contestEffect.setScore(SCORE);
        contestEffect.setJam(JAM);

        assertEquals(DBID, contestEffect.getDbid());
        assertEquals(DESCRIPTION, contestEffect.getDescription());
        assertEquals(NAME, contestEffect.getName());
        assertEquals(SCORE, contestEffect.getScore());
        assertEquals(JAM, contestEffect.getJam());
    }

    @Test
    public void testConstructor() {
        ContestEffectRequest input = new ContestEffectRequest();
        input.setDescription(DESCRIPTION);
        input.setName(NAME);
        input.setScore(SCORE);
        input.setJam(JAM);

        ContestEffect contestEffect = new ContestEffect(input);
        assertEquals(DESCRIPTION, contestEffect.getDescription());
        assertEquals(NAME, contestEffect.getName());
        assertEquals(SCORE, contestEffect.getScore());
        assertEquals(JAM, contestEffect.getJam());
    }
}