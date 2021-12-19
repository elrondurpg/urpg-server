package com.pokemonurpg.stats.models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class ChampionVictoryKeyTest {
    private final static Integer CHALLENGER_DBID = 43242;
    private final static String DEFENDER_NAME = "DEFENDER_NAME";

    @Test
    public void testPojo() {
        assertNotNull(new ChampionVictoryKey());
    }
/*

    @Test
    public void testConstructor() {
        ChampionVictoryKey key = new ChampionVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        assertEquals(CHALLENGER_DBID, key.getChallengerDbid());
        assertEquals(DEFENDER_NAME, key.getDefender());
    }

    @Test
    public void testEqualsAndHashCode() {
        ChampionVictoryKey key = new ChampionVictoryKey();

        assertTrue(key.equals(key));
        assertFalse(key.equals(null));
        assertFalse(key.equals(new Object()));

        ChampionVictoryKey key2 = new ChampionVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        ChampionVictoryKey key3 = new ChampionVictoryKey(CHALLENGER_DBID, DEFENDER_NAME);
        assertTrue(key2.equals(key3));

        assertEquals(Objects.hash(CHALLENGER_DBID, DEFENDER_NAME), key2.hashCode());
    }
*/

}