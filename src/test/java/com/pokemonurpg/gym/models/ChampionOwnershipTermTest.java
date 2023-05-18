package com.pokemonurpg.gym.models;

import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermInputDto;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ChampionOwnershipTermTest {
    private final static Date       DATE        = new Date();
    private final static Champion CHAMPION    = mock(Champion.class);
    private final static Integer    DBID        = 342;
    private final static Integer    DRAWS       = 3;
    private final static Integer    LOSSES      = 2;
    private final static Integer    WINS        = 1;
    private final static Member     OWNER       = mock(Member.class);

    @Test
    public void testPojo() {
        ChampionOwnershipTerm term = new ChampionOwnershipTerm();
        term.setDbid(DBID);
        term.setSlot(CHAMPION);
        term.setOwner(OWNER);

        assertEquals(DBID, term.getDbid());
        assertEquals(CHAMPION, term.getSlot());
        assertEquals(OWNER, term.getOwner());
    }

    @Test
    public void testConstructor() {
        ChampionOwnershipTermInputDto input = new ChampionOwnershipTermInputDto();
        input.setDraws(DRAWS);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setOpenDate(DATE);

        ChampionOwnershipTerm term = new ChampionOwnershipTerm(input);
        assertEquals(DRAWS, term.getDraws());
        assertEquals(WINS, term.getWins());
        assertEquals(LOSSES, term.getLosses());
        assertEquals(DATE, term.getOpenDate());
    }
}