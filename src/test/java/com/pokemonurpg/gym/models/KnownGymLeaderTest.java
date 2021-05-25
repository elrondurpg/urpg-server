package com.pokemonurpg.gym.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnownGymLeaderTest {
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownGymLeader model = new KnownGymLeader();
        model.setName(NAME);

        assertEquals(NAME, model.getName());
    }

    @Test
    public void testConstructor() {
        KnownGymLeader model = new KnownGymLeader(NAME);
        assertEquals(NAME, model.getName());
    }
}