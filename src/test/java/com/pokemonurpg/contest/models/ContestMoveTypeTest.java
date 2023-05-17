package com.pokemonurpg.contest.models;

import com.pokemonurpg.configuration.v1.contestmovetypes.ContestMoveTypeInputDto;
import com.pokemonurpg.entities.ContestMoveType;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContestMoveTypeTest {
    private final static String NAME = "NAME";
    private final static String DESCRIPTION = "DESCRIPTION";
    private final static Integer SCORE = 32432;
    private final static Integer JAM = 23423;
    private final static Integer DBID = 32432;

    @Test
    public void testPojo() {
        ContestMoveType contestMoveType = new ContestMoveType();
        contestMoveType.setDbid(DBID);
        contestMoveType.setDescription(DESCRIPTION);
        contestMoveType.setName(NAME);
        contestMoveType.setScore(SCORE);
        contestMoveType.setJam(JAM);

        assertEquals(DBID, contestMoveType.getDbid());
        assertEquals(DESCRIPTION, contestMoveType.getDescription());
        assertEquals(NAME, contestMoveType.getName());
        assertEquals(SCORE, contestMoveType.getScore());
        assertEquals(JAM, contestMoveType.getJam());
    }

    @Test
    public void testConstructor() {
        ContestMoveTypeInputDto input = new ContestMoveTypeInputDto();
        input.setDescription(DESCRIPTION);
        input.setName(NAME);
        input.setScore(SCORE);
        input.setJam(JAM);

        ContestMoveType contestMoveType = new ContestMoveType(input);
        assertEquals(DESCRIPTION, contestMoveType.getDescription());
        assertEquals(NAME, contestMoveType.getName());
        assertEquals(SCORE, contestMoveType.getScore());
        assertEquals(JAM, contestMoveType.getJam());
    }
}