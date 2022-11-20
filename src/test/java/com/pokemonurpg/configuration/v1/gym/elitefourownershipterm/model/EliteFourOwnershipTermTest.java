package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;

public class EliteFourOwnershipTermTest {
    @Test
    public void test_setDefaultValues() {
        EliteFourOwnershipTerm model = new EliteFourOwnershipTerm();
        model.setDefaultValues();
        assertEquals(GymConstants.DEFAULT_WINS, model.getWins());
        assertEquals(GymConstants.DEFAULT_LOSSES, model.getLosses());
        assertEquals(GymConstants.DEFAULT_DRAWS, model.getDraws());
    }
    
}
