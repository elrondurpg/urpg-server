package com.pokemonurpg.configuration.v1.pokemon.species.model;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.pokemon.species.constants.SpeciesConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpeciesTest {
    private static final String NAME = "NAME";
    
    @Test
    public void test_formulaGettersAndSetters() {
        Species species = new Species();
        species.setFullyEvolved(true);
        species.setEvolved(true);
        species.setMegaEvolved(true);

        assertTrue(species.getFullyEvolved());
        assertTrue(species.getEvolved());
        assertTrue(species.getMegaEvolved());
    }

    @Test
    public void test_setDefaultValues() {
        Species species = new Species();
        species.setName(NAME);
        species.setDefaultValues();

        assertEquals(NAME, species.getDisplayName());
        assertEquals(SpeciesConstants.DEFAULT_BATTLE_ONLY, species.getBattleOnly());
        assertEquals(SpeciesConstants.DEFAULT_LEGENDARY_TIER, species.getLegendaryTier());
        assertEquals(SpeciesConstants.DEFAULT_FEMALE_ALLOWED, species.getFemaleAllowed());
        assertEquals(SpeciesConstants.DEFAULT_MALE_ALLOWED, species.getMaleAllowed());
    }

}