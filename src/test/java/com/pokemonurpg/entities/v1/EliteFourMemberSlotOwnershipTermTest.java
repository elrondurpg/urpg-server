package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordRequest;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EliteFourMemberSlotOwnershipTermTest {
    private final static Date       DATE        = new Date();
    private final static EliteFourMemberSlot ELITE_FOUR  = mock(EliteFourMemberSlot.class);
    private final static Integer    DBID        = 342;
    private final static Integer    DRAWS       = 3;
    private final static Integer    LOSSES      = 2;
    private final static Integer    WINS        = 1;
    private final static Member     OWNER       = mock(Member.class);

    @Test
    public void testPojo() {
        EliteFourMemberRecord term = new EliteFourMemberRecord();
        term.setDbid(DBID);
        term.setSlot(ELITE_FOUR);
        term.setOwner(OWNER);

        assertEquals(DBID, term.getDbid());
        assertEquals(ELITE_FOUR, term.getSlot());
        assertEquals(OWNER, term.getOwner());
    }

    @Test
    public void testConstructor() {
        EliteFourRecordRequest input = new EliteFourRecordRequest();
        input.setDraws(DRAWS);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setOpenDate(DATE);

        EliteFourMemberRecord term = new EliteFourMemberRecord(input);
        assertEquals(DRAWS, term.getDraws());
        assertEquals(WINS, term.getWins());
        assertEquals(LOSSES, term.getLosses());
        assertEquals(DATE, term.getOpenDate());
    }
}