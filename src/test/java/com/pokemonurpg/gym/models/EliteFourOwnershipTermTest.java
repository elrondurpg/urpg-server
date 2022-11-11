package com.pokemonurpg.gym.models;

import com.pokemonurpg.gym.input.EliteFourOwnershipTermInputDto;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class EliteFourOwnershipTermTest {
    private final static Date       DATE        = new Date();
    private final static EliteFour  ELITE_FOUR  = mock(EliteFour.class);
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