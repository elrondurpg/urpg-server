package com.pokemonurpg.configuration.v1.gym.championownershipterm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;

public class ChampionOwnershipTermTest {
    @Test
    public void test_setDefaultValues() {
        ChampionOwnershipTerm model = new ChampionOwnershipTerm();
        model.setDefaultValues();
        assertEquals(GymConstants.DEFAULT_WINS, model.getWins());
        assertEquals(GymConstants.DEFAULT_LOSSES, model.getLosses());
        assertEquals(GymConstants.DEFAULT_DRAWS, model.getDraws());
    }
}
