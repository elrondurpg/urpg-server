package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.models.EliteFourOwnershipTerm;
import com.pokemonurpg.gym.service.EliteFourOwnershipTermService;
import com.pokemonurpg.gym.service.EliteFourService;
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
public class BelongsToThisEliteFourSlotValidatorTest {
    private final static EliteFour              ELITE_FOUR              = mock(EliteFour.class);
    private final static EliteFour              OTHER_ELITE_FOUR        = mock(EliteFour.class);
    private final static EliteFourOwnershipTerm TERM                    = mock(EliteFourOwnershipTerm.class);
    private final static Integer                DBID                    = 1;
    private final static Integer                ELITE_FOUR_DBID         = 2;
    private final static Integer                OTHER_ELITE_FOUR_DBID   = 3;
    private final static Integer                REQUEST_TERM_DBID       = 4;

    @InjectMocks
    private BelongsToThisEliteFourSlotValidator belongsToThisEliteFourValidator;

    @Mock
    private EliteFourService eliteFourService;

    @Mock
    private EliteFourOwnershipTermService eliteFourOwnershipTermService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(belongsToThisEliteFourValidator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestEliteFourIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourService.findByDbid(DBID)).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestTermIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenTermHasNoEliteFour() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(null);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestEliteFourAndTermEliteFourAreDifferent() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(OTHER_ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        when(OTHER_ELITE_FOUR.getDbid()).thenReturn(OTHER_ELITE_FOUR_DBID);
        assertFalse(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenRequestEliteFourAndTermEliteFourMatch() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(eliteFourService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(eliteFourOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        assertTrue(belongsToThisEliteFourValidator.isValid(REQUEST_TERM_DBID, null));
    }
}