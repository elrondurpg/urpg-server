package com.pokemonurpg.gym.validation;

import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BelongsToThisGymValidatorTest {
    private final static Gym                GYM                 = mock(Gym.class);
    private final static Gym                OTHER_GYM           = mock(Gym.class);
    private final static GymOwnershipTerm   TERM                = mock(GymOwnershipTerm.class);
    private final static Integer            DBID                = 1;
    private final static Integer            GYM_DBID            = 2;
    private final static Integer            OTHER_GYM_DBID      = 3;
    private final static Integer            REQUEST_TERM_DBID   = 4;

    @InjectMocks
    private BelongsToThisGymValidator belongsToThisGymValidator;

    @Mock
    private GymService gymService;

    @Mock
    private GymOwnershipTermService gymOwnershipTermService;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(belongsToThisGymValidator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestGymIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(gymService.findByDbid(DBID)).thenReturn(null);
        assertFalse(belongsToThisGymValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestTermIsNull() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(gymService.findByDbid(DBID)).thenReturn(GYM);
        when(gymOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(null);
        assertFalse(belongsToThisGymValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenTermHasNoGym() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(gymService.findByDbid(DBID)).thenReturn(GYM);
        when(gymOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getGym()).thenReturn(null);
        assertFalse(belongsToThisGymValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenRequestGymAndTermGymAreDifferent() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(gymService.findByDbid(DBID)).thenReturn(GYM);
        when(gymOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getGym()).thenReturn(OTHER_GYM);
        when(GYM.getDbid()).thenReturn(GYM_DBID);
        when(OTHER_GYM.getDbid()).thenReturn(OTHER_GYM_DBID);
        assertFalse(belongsToThisGymValidator.isValid(REQUEST_TERM_DBID, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenRequestGymAndTermGymMatch() {
        when(requestPathVariableService.findIntByName("dbid")).thenReturn(DBID);
        when(gymService.findByDbid(DBID)).thenReturn(GYM);
        when(gymOwnershipTermService.findByDbid(REQUEST_TERM_DBID)).thenReturn(TERM);
        when(TERM.getGym()).thenReturn(GYM);
        when(GYM.getDbid()).thenReturn(GYM_DBID);
        assertTrue(belongsToThisGymValidator.isValid(REQUEST_TERM_DBID, null));
    }
}