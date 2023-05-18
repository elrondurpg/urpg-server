package com.pokemonurpg.entities.v1;

import com.pokemonurpg.entities.v1.KnownGymLeader;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnownGymLeaderTest {
    private final static Integer DBID = 4324;
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownGymLeader model = new KnownGymLeader();
        model.setName(NAME);
        model.setDbid(DBID);

        assertEquals(NAME, model.getName());
        assertEquals(DBID, model.getDbid());
    }

    @Test
    public void testConstructor() {
        KnownGymLeader model = new KnownGymLeader(NAME);
        assertEquals(NAME, model.getName());
    }
}