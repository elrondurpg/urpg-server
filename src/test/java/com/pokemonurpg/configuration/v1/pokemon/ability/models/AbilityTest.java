package com.pokemonurpg.configuration.v1.pokemon.ability.models;

import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AbilityTest {
    private final static Integer DBID = 2893742;
    private final static String NAME = "ABILITY";
    private final static String DESCRIPTION = "TEST DESCRIPTION";

    @Test
    public void testPojo() {
        Ability ability = new Ability();
        ability.setDbid(DBID);
        assertEquals(DBID, ability.getDbid());

        Set<SpeciesAbility> pokemon = new HashSet<>();
        ability.setPokemon(pokemon);
        assertEquals(pokemon, ability.getPokemon());
    }

    @Test
    public void updateFromAbilityInputDto() {
        AbilityInputDto input = new AbilityInputDto();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Ability ability = new Ability();
        ability.update(input);
        assertEquals(NAME, ability.getName());
        assertEquals(DESCRIPTION, ability.getDescription());
    }

    @Test
    public void constructFromAbilityInputDto() {
        AbilityInputDto input = new AbilityInputDto();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Ability ability = new Ability(input);
        assertEquals(NAME, ability.getName());
        assertEquals(DESCRIPTION, ability.getDescription());
    }

}