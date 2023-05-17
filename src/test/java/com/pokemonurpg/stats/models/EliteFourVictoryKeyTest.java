package com.pokemonurpg.stats.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EliteFourVictoryKeyTest {
    private final static Integer CHALLENGER_DBID = 43242;
    private final static String DEFENDER_NAME = "DEFENDER_NAME";

    @Test
    public void testPojo() {
        assertNotNull(new EliteFourVictoryKey());
    }
/*
    @Test
    public void testConstructor() {
        EliteFourVictoryKey key = new EliteFourVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        assertEquals(CHALLENGER_DBID, key.getChallengerDbid());
        assertEquals(DEFENDER_NAME, key.getDefender());
    }

    @Test
    public void testEqualsAndHashCode() {
        EliteFourVictoryKey key = new EliteFourVictoryKey();

        assertTrue(key.equals(key));
        assertFalse(key.equals(null));
        assertFalse(key.equals(new Object()));

        EliteFourVictoryKey key2 = new EliteFourVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        EliteFourVictoryKey key3 = new EliteFourVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        assertTrue(key2.equals(key3));

        assertEquals(Objects.hash(CHALLENGER_DBID, DEFENDER_NAME), key2.hashCode());
    }*/

}