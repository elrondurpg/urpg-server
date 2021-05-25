package com.pokemonurpg.gym.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnownChampionTest {
    private final static String NAME = "NAME";

    @Test
    public void testPojo() {
        KnownChampion model = new KnownChampion();
        model.setName(NAME);

        assertEquals(NAME, model.getName());
    }

    @Test
    public void testConstructor() {
        KnownChampion model = new KnownChampion(NAME);
        assertEquals(NAME, model.getName());
    }
}