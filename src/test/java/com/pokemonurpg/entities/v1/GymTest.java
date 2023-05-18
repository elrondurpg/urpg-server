package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.gyms.GymRequest;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GymTest {
    private final static Badge BADGE       = mock(Badge.class);
    private final static GymLeaderRecord OWNER_REC   = mock(GymLeaderRecord.class);
    private final static Integer            DBID        = 32432;
    private final static List<GymVictory>   VICTORIES   = new ArrayList<>();
    private final static String             NAME        = "NAME";
    private final static Set<OwnedPokemon>  POKEMON     = new HashSet<>();
    private final static Type               TYPE        = mock(Type.class);

    @Test
    public void testPojo() {
        Gym gym = new Gym();
        gym.setDbid(DBID);
        gym.setName(NAME);
        gym.setBadge(BADGE);
        gym.setPokemon(POKEMON);
        gym.setType(TYPE);
        gym.setVictories(VICTORIES);
        gym.setCurrentOwnerRecord(OWNER_REC);

        assertEquals(DBID, gym.getDbid());
        assertEquals(NAME, gym.getName());
        assertEquals(BADGE, gym.getBadge());
        assertEquals(POKEMON, gym.getPokemon());
        assertEquals(TYPE, gym.getType());
        assertEquals(VICTORIES, gym.getVictories());
        assertEquals(OWNER_REC, gym.getCurrentOwnerRecord());
    }

    @Test
    public void testConstructor() {
        GymRequest input = new GymRequest();
        input.setName(NAME);

        Gym gym = new Gym(input);
        assertEquals(NAME, gym.getName());
    }

    @Test
    public void testConstructorWithDefaultValues() {
        GymRequest input = new GymRequest();
        input.setName(NAME);

        Gym gym = new Gym(input);
        assertEquals(NAME, gym.getName());
    }


}