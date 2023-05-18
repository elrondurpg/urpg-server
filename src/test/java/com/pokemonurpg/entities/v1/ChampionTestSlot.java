package com.pokemonurpg.entities.v1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChampionTestSlot {
    private final static Integer DBID = 4324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        Champion model = new Champion();
        model.setName(NAME);
        model.setDbid(DBID);

        assertEquals(NAME, model.getName());
        assertEquals(DBID, model.getDbid());
    }

    @Test
    public void testConstructor() {
        Champion model = new Champion(NAME);
        assertEquals(NAME, model.getName());
    }
}