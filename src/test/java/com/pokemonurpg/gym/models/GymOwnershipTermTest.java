package com.pokemonurpg.gym.models;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.entities.Item;
import com.pokemonurpg.entities.Member;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GymOwnershipTermTest {
    private final static Date       DATE    = new Date();
    private final static Gym GYM     = mock(Gym.class);
    private final static GymLeague LEAGUE  = mock(GymLeague.class);
    private final static Integer    DBID    = 342;
    private final static Integer    DRAWS   = 3;
    private final static Integer    LOSSES  = 2;
    private final static Integer    WINS    = 1;
    private final static Item       TM      = mock(Item.class);
    private final static Member     OWNER  = mock(Member.class);

    @Test
    public void testPojo() {
        GymOwnershipTerm term = new GymOwnershipTerm();
        term.setDbid(DBID);
        term.setGym(GYM);
        term.setLeague(LEAGUE);
        term.setTm(TM);
        term.setOwner(OWNER);

        assertEquals(DBID, term.getDbid());
        assertEquals(GYM, term.getGym());
        assertEquals(LEAGUE, term.getLeague());
        assertEquals(TM, term.getTm());
        assertEquals(OWNER, term.getOwner());
    }

    @Test
    public void testConstructor() {
        GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();
        input.setDraws(DRAWS);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setOpenDate(DATE);

        GymOwnershipTerm term = new GymOwnershipTerm(input);
        assertEquals(DRAWS, term.getDraws());
        assertEquals(WINS, term.getWins());
        assertEquals(LOSSES, term.getLosses());
        assertEquals(DATE, term.getOpenDate());
    }
}