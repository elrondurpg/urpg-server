package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.entities.v1.EliteFourMemberSlot;
import com.pokemonurpg.entities.v1.EliteFourMemberRecord;
import com.pokemonurpg.configuration.v1.elitefourrecords.EliteFourRecordService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BelongsToThisEliteFourSlotValidatorTestMemberMemberMemberSlot {
    private final static EliteFourMemberSlot ELITE_FOUR              = mock(EliteFourMemberSlot.class);
    private final static EliteFourMemberSlot OTHER_ELITE_FOUR        = mock(EliteFourMemberSlot.class);
    private final static EliteFourMemberRecord TERM                    = mock(EliteFourMemberRecord.class);
    private final static Integer                DBID                    = 1;
    private final static Integer                ELITE_FOUR_DBID         = 2;
    private final static Integer                OTHER_ELITE_FOUR_DBID   = 3;
    private final static Integer                REQUEST_TERM_DBID       = 4;

    @InjectMocks
    private BelongsToThisEliteFourMemberSlotValidator belongsToThisEliteFourValidator;

    @Mock
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Mock
    private EliteFourRecordService eliteFourRecordService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(belongsToThisEliteFourValidator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestEliteFourIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourMemberSlotService.findByDbid(DBID)).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestTermIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourMemberSlotService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourRecordService.findByDbid(REQUEST_TERM_DBID)).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenTermHasNoEliteFour() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourMemberSlotService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourRecordService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestEliteFourAndTermEliteFourAreDifferent() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourMemberSlotService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourRecordService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(OTHER_ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        when(OTHER_ELITE_FOUR.getDbid()).thenReturn(OTHER_ELITE_FOUR_DBID);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenRequestEliteFourAndTermEliteFourMatch() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourMemberSlotService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourRecordService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        assertTrue(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }
}