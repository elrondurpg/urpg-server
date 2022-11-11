package com.pokemonurpg.stats.models;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class GymVictoryKeyTest {
    private final static Integer CHALLENGER_DBID = 43242;
    private final static String DEFENDER_NAME = "DEFENDER_NAME";
    private final static Integer GYM_DBID = 34212;
    private final static Integer LEAGUE_DBID = 43222;

    @Test
    public void testPojo() {
        assertNotNull(new GymVictoryKey());
    }
/*
    @Test
    public void testConstructor() {
        GymVictoryKey key = new GymVictoryKey(CHALLENGER_DBID, DEFENDER_NAME, GYM_DBID, LEAGUE_DBID);
        assertEquals(CHALLENGER_DBID, key.getChallengerDbid());
        assertEquals(DEFENDER_NAME, key.getDefender());
        assertEquals(GYM_DBID, key.getGymDbid());
        assertEquals(LEAGUE_DBID, key.getLeagueDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        GymVictoryKey key = new GymVictoryKey();

        assertTrue(key.equals(key));
        assertFalse(key.equals(null));
        assertFalse(key.equals(new Object()));

        GymVictoryKey key2 = new GymVictoryKey(CHALLENGER_DBID, DEFENDER_NAME, GYM_DBID, LEAGUE_DBID);
        GymVictoryKey key3 = new GymVictoryKey(CHALLENGER_DBID, DEFENDER_NAME, GYM_DBID, LEAGUE_DBID);
        assertTrue(key2.equals(key3));

        assertEquals(Objects.hash(CHALLENGER_DBID, DEFENDER_NAME, GYM_DBID, LEAGUE_DBID), key2.hashCode());
    }*/

}