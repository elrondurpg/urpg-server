package com.pokemonurpg.species.models;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesAbilityInputDto;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.entities.v1.SpeciesAbility;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpeciesAbilityTest {
    private final static Species SPECIES = new Species();
    private final static Ability ABILITY = new Ability();
    private final static Boolean HIDDEN = true;

    @Test
    public void testPojo() {
        SpeciesAbility speciesAbility = new SpeciesAbility();
        speciesAbility.setAbility(ABILITY);
        speciesAbility.setSpecies(SPECIES);
        speciesAbility.setHidden(HIDDEN);

        assertEquals(ABILITY, speciesAbility.getAbility());
        assertEquals(SPECIES, speciesAbility.getSpecies());
        assertEquals(HIDDEN, speciesAbility.getHidden());
    }

    @Test
    public void testConstructor() {
        SpeciesAbilityInputDto input = new SpeciesAbilityInputDto();
        input.setHidden(HIDDEN);

        SpeciesAbility speciesAbility = new SpeciesAbility(input, SPECIES, ABILITY);
        assertEquals(HIDDEN, speciesAbility.getHidden());
        assertEquals(SPECIES, speciesAbility.getSpecies());
        assertEquals(ABILITY, speciesAbility.getAbility());

        assertNotNull(speciesAbility.id);
    }

}