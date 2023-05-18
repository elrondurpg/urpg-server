package com.pokemonurpg.gym.models;

import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.entities.v1.EliteFourOwnershipTerm;
import com.pokemonurpg.entities.v1.Member;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EliteFourOwnershipTermTest {
    private final static Date       DATE        = new Date();
    private final static EliteFour ELITE_FOUR  = mock(EliteFour.class);
    private final static Integer    DBID        = 342;
    private final static Integer    DRAWS       = 3;
    private final static Integer    LOSSES      = 2;
    private final static Integer    WINS        = 1;
    private final static Member     OWNER       = mock(Member.class);

    @Test
    public void testPojo() {
        EliteFourOwnershipTerm term = new EliteFourOwnershipTerm();
        term.setDbid(DBID);
        term.setSlot(ELITE_FOUR);
        term.setOwner(OWNER);

        assertEquals(DBID, term.getDbid());
        assertEquals(ELITE_FOUR, term.getSlot());
        assertEquals(OWNER, term.getOwner());
    }

    @Test
    public void testConstructor() {
        EliteFourOwnershipTermInputDto input = new EliteFourOwnershipTermInputDto();
        input.setDraws(DRAWS);
        input.setWins(WINS);
        input.setLosses(LOSSES);
        input.setOpenDate(DATE);

        EliteFourOwnershipTerm term = new EliteFourOwnershipTerm(input);
        assertEquals(DRAWS, term.getDraws());
        assertEquals(WINS, term.getWins());
        assertEquals(LOSSES, term.getLosses());
        assertEquals(DATE, term.getOpenDate());
    }
}