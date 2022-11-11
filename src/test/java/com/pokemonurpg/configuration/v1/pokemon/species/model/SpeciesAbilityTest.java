package com.pokemonurpg.configuration.v1.pokemon.species.model;

import org.junit.jupiter.api.Test;

import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;

import static org.junit.jupiter.api.Assertions.*;

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
    }

}