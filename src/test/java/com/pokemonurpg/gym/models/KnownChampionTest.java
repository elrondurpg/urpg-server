package com.pokemonurpg.gym.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnownChampionTest {
    private final static Integer DBID = 4324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownChampion model = new KnownChampion();
        model.setName(NAME);
        model.setDbid(DBID);

        assertEquals(NAME, model.getName());
        assertEquals(DBID, model.getDbid());
    }

    @Test
    public void testConstructor() {
        KnownChampion model = new KnownChampion(NAME);
        assertEquals(NAME, model.getName());
    }
}