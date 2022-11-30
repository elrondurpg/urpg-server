package com.pokemonurpg.configuration.v1.member.member.model;

import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EliteFourVictoryTest {
    private final static Member CHALLENGER = new Member();
    private final static String DEFENDER = "DEFENDER_NAME";
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";
/*
    @Test
    public void testPojo() {
        EliteFourVictory model = new EliteFourVictory();
        model.setDefender(DEFENDER);
        model.setChallenger(CHALLENGER);
        model.setDate(DATE);
        model.setLogUrl(LOG_URL);

        assertEquals(CHALLENGER, model.getChallenger());
        assertEquals(DEFENDER, model.getDefender());
        assertEquals(DATE, model.getDate());
        assertEquals(LOG_URL, model.getLogUrl());
    }

    @Test
    public void testConstructor() {
        CHALLENGER.setDbid(342);

        EliteFourVictoryInputDto input = new EliteFourVictoryInputDto();
        input.setDate(DATE);
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);

        EliteFourVictory model = new EliteFourVictory(input, CHALLENGER);
        assertEquals(CHALLENGER, model.getChallenger());
        assertEquals(DEFENDER, model.getDefender());
        assertEquals(DATE, model.getDate());
        assertEquals(LOG_URL, model.getLogUrl());
    }*/
}