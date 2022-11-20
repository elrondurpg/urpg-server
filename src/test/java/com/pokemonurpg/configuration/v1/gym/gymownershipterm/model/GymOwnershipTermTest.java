package com.pokemonurpg.configuration.v1.gym.gymownershipterm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;

public class GymOwnershipTermTest {
    @Test
    public void test_setDefaultValues() {
        GymOwnershipTerm model = new GymOwnershipTerm();
        model.setDefaultValues();
        assertEquals(GymConstants.DEFAULT_WINS, model.getWins());
        assertEquals(GymConstants.DEFAULT_LOSSES, model.getLosses());
        assertEquals(GymConstants.DEFAULT_DRAWS, model.getDraws());
    }
    
}