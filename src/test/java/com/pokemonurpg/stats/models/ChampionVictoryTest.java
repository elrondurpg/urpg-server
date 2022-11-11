package com.pokemonurpg.stats.models;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.ChampionVictoryInputDto;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChampionVictoryTest {
    private final static Member CHALLENGER = new Member();
    private final static String DEFENDER = "DEFENDER_NAME";
    private final static Date DATE = new Date();
    private final static String LOG_URL = "LOG_URL";

    /*@Test
    public void testPojo() {
        ChampionVictory model = new ChampionVictory();
        model.setChallenger(CHALLENGER);
        model.setDefender(DEFENDER);
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

        ChampionVictoryInputDto input = new ChampionVictoryInputDto();
        input.setDate(DATE);
        input.setDefender(DEFENDER);
        input.setLogUrl(LOG_URL);

        ChampionVictory model = new ChampionVictory(input, CHALLENGER);
        assertEquals(CHALLENGER, model.getChallenger());
        assertEquals(DEFENDER, model.getDefender());
        assertEquals(DATE, model.getDate());
        assertEquals(LOG_URL, model.getLogUrl());
    }*/
}