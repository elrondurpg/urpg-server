package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.abilities.AbilityRequest;
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

        Set<PokemonAbility> pokemon = new HashSet<>();
        ability.setPokemon(pokemon);
        assertEquals(pokemon, ability.getPokemon());
    }

    @Test
    public void updateFromAbilityInputDto() {
        AbilityRequest input = new AbilityRequest();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Ability ability = new Ability();
        ability.update(input);
        assertEquals(NAME, ability.getName());
        assertEquals(DESCRIPTION, ability.getDescription());
    }

    @Test
    public void constructFromAbilityInputDto() {
        AbilityRequest input = new AbilityRequest();
        input.setName(NAME);
        input.setDescription(DESCRIPTION);

        Ability ability = new Ability(input);
        assertEquals(NAME, ability.getName());
        assertEquals(DESCRIPTION, ability.getDescription());
    }

}