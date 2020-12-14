package com.pokemonurpg.gym.models;

import com.pokemonurpg.gym.input.GymLeagueInputDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GymLeagueTest {
    private final static Integer DBID = 3242;
    private final static String NAME = "NAME";
    private final static List<Gym> GYMS = new ArrayList<>();

    @Test
    public void testPojo() {
        GymLeague gymLeague = new GymLeague();
        gymLeague.setDbid(DBID);
        gymLeague.setName(NAME);
        gymLeague.setGyms(GYMS);

        assertEquals(DBID, gymLeague.getDbid());
        assertEquals(NAME, gymLeague.getName());
        assertEquals(GYMS, gymLeague.getGyms());
    }

    @Test
    public void testConstructor() {
        GymLeagueInputDto input = new GymLeagueInputDto();
        input.setName(NAME);

        GymLeague gymLeague = new GymLeague(input);
        assertEquals(NAME, gymLeague.getName());
    }

}