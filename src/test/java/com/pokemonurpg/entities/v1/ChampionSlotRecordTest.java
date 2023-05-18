package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.championrecords.ChampionRecordRequest;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ChampionSlotRecordTest {
    private final static Date       DATE        = new Date();
    private final static ChampionSlot CHAMPION_SLOT = mock(ChampionSlot.class);
    private final static Integer    DBID        = 342;
    private final static Integer    DRAWS       = 3;
    private final static Integer    LOSSES      = 2;
    private final static Integer    WINS        = 1;
    private final static Member     OWNER       = mock(Member.class);

    @Test
    public void testPojo() {
        ChampionRecord term = new ChampionRecord();
        term.setDbid(DBID);
        term.setSlot(CHAMPION_SLOT);
        term.setOwner(OWNER);

        assertEquals(DBID, term.getDbid());
        assertEquals(CHAMPION_SLOT, term.getSlot());
        assertEquals(OWNER, term.getOwner());
    }

    @Test
    public void testConstructor() {
        ChampionRecordRequest input = new ChampionRecordRequest();
        input.setDraws(DRAWS);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setOpenDate(DATE);

        ChampionRecord term = new ChampionRecord(input);
        assertEquals(DRAWS, term.getDraws());
        assertEquals(WINS, term.getWins());
        assertEquals(LOSSES, term.getLosses());
        assertEquals(DATE, term.getOpenDate());
    }
}