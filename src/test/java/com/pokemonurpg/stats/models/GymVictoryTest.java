package com.pokemonurpg.stats.models;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.GymVictoryInputDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class GymVictoryTest {
    private final static Member CHALLENGER = new Member();
    private final static String DEFENDER = "DEFENDER_NAME";
    private final static Gym GYM = new Gym();
    private final static GymLeague LEAGUE = new GymLeague();
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";

    @Test
    public void testPojo() {
        GymVictory model = new GymVictory();
        model.setChallenger(CHALLENGER);
        model.setDefender(DEFENDER);
        model.setGym(GYM);
        model.setLeague(LEAGUE);
        model.setDate(DATE);
        model.setLogUrl(LOG_URL);

        assertEquals(CHALLENGER, model.getChallenger());
        assertEquals(DEFENDER, model.getDefender());
        assertEquals(GYM, model.getGym());
        assertEquals(LEAGUE, model.getLeague());
        assertEquals(DATE, model.getDate());
        assertEquals(LOG_URL, model.getLogUrl());
    }

    @Test
    public void testConstructor() {
        CHALLENGER.setDbid(342);
        GYM.setDbid(4324);
        LEAGUE.setDbid(932);

        GymVictoryInputDto input = new GymVictoryInputDto();
        input.setDate(DATE);
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);

        GymVictory model = new GymVictory(input, CHALLENGER, GYM, LEAGUE);
        assertEquals(CHALLENGER, model.getChallenger());
        assertEquals(DEFENDER, model.getDefender());
        assertEquals(GYM, model.getGym());
        assertEquals(LEAGUE, model.getLeague());
        assertEquals(DATE, model.getDate());
        assertEquals(LOG_URL, model.getLogUrl());
    }
}