package com.pokemonurpg.gym.validation;

import com.pokemonurpg.lib.v1.validators.BelongsToThisChampionSlotValidator;
import com.pokemonurpg.lib.v1.service.RequestPathVariableService;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.championrecords.ChampionOwnershipTermService;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;
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
public class BelongsToThisChampionSlotValidatorTest {
    private final static Champion              ELITE_FOUR              = mock(Champion.class);
    private final static Champion              OTHER_ELITE_FOUR        = mock(Champion.class);
    private final static ChampionOwnershipTerm TERM                    = mock(ChampionOwnershipTerm.class);
    private final static Integer                DBID                    = 1;
    private final static Integer                ELITE_FOUR_DBID         = 2;
    private final static Integer                OTHER_ELITE_FOUR_DBID   = 3;
    private final static Integer                REQUEST_TERM_DBID       = 4;

    @InjectMocks
    private BelongsToThisChampionSlotValidator belongsToThisChampionValidator;

    @Mock
    private ChampionService championService;

    @Mock
    private ChampionOwnershipTermService championOwnershipTermService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(belongsToThisChampionValidator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestChampionIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(championService.findByDbid(DBID)).thenReturn(null);
        assertFalse(belongsToThisChampionValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestTermIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(championService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(championOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(null);
        assertFalse(belongsToThisChampionValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenTermHasNoChampion() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(championService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(championOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(null);
        assertFalse(belongsToThisChampionValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestChampionAndTermChampionAreDifferent() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(championService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(championOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(OTHER_ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        when(OTHER_ELITE_FOUR.getDbid()).thenReturn(OTHER_ELITE_FOUR_DBID);
        assertFalse(belongsToThisChampionValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenRequestChampionAndTermChampionMatch() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(championService.findByDbid(DBID)).thenReturn(ELITE_FOUR);
        when(championOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getSlot()).thenReturn(ELITE_FOUR);
        when(ELITE_FOUR.getDbid()).thenReturn(ELITE_FOUR_DBID);
        assertTrue(belongsToThisChampionValidator.isValid(REQUEST_TERM_DBID, null));
    }
}