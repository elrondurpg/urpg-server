package com.pokemonurpg.entities.v1;

import com.pokemonurpg.entities.v1.KnownChampion;
import org.junit.Test;

import static org.junit.Assert.*;

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