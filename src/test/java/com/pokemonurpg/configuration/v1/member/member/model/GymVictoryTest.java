package com.pokemonurpg.configuration.v1.member.member.model;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GymVictoryTest {
    private final static Member CHALLENGER = new Member();
    private final static String DEFENDER = "DEFENDER_NAME";
    private final static Gym GYM = new Gym();
    private final static League LEAGUE = new League();
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";
/*
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
    }*/
}