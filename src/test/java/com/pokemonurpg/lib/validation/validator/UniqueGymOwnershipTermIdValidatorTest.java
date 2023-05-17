package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermInputDto;
import com.pokemonurpg.entities.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueGymOwnershipTermIdValidatorTest {
    private final static Date   OPEN_DATE   = new Date();
    private final static String GYM_NAME    = "GYM_NAME";
    private final static String OWNER_NAME  = "OWNER_NAME";

    @InjectMocks
    private UniqueGymOwnershipTermIdValidator validator;

    @Mock
    private GymOwnershipTermService gymOwnershipTermService;

    @Test
    public void initialize() {
        validator.initialize(null);
    }

    @Test
    public void isValid_ReturnsTrue_WhenInputIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    public void isValid_ReturnsFalse_WhenIdIsNotUnique() {
        when(gymOwnershipTermService.findByGymAndOwnerAndOpenDate(GYM_NAME, OWNER_NAME, OPEN_DATE)).thenReturn(new GymOwnershipTerm());
        GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();
        input.setGym(GYM_NAME);
        input.setOwner(OWNER_NAME);
        input.setOpenDate(OPEN_DATE);
        assertFalse(validator.isValid(input, null));
    }

    @Test
    public void isValid_ReturnsTrue_WhenIdIsUnique() {
        when(gymOwnershipTermService.findByGymAndOwnerAndOpenDate(GYM_NAME, OWNER_NAME, OPEN_DATE)).thenReturn(null);
        GymOwnershipTermInputDto input = new GymOwnershipTermInputDto();
        input.setGym(GYM_NAME);
        input.setOwner(OWNER_NAME);
        input.setOpenDate(OPEN_DATE);
        assertTrue(validator.isValid(input, null));
    }

}