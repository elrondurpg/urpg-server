package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.pokemon.PokemonAbilityRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonAbilityTest {
    private final static Pokemon POKEMON = new Pokemon();
    private final static Ability ABILITY = new Ability();
    private final static Boolean HIDDEN = true;

    @Test
    public void testPojo() {
        PokemonAbility pokemonAbility = new PokemonAbility();
        pokemonAbility.setAbility(ABILITY);
        pokemonAbility.setSpecies(POKEMON);
        pokemonAbility.setHidden(HIDDEN);

        assertEquals(ABILITY, pokemonAbility.getAbility());
        assertEquals(POKEMON, pokemonAbility.getSpecies());
        assertEquals(HIDDEN, pokemonAbility.getHidden());
    }

    @Test
    public void testConstructor() {
        PokemonAbilityRequest input = new PokemonAbilityRequest();
        input.setHidden(HIDDEN);

        PokemonAbility pokemonAbility = new PokemonAbility(input, POKEMON, ABILITY);
        assertEquals(HIDDEN, pokemonAbility.getHidden());
        assertEquals(POKEMON, pokemonAbility.getSpecies());
        assertEquals(ABILITY, pokemonAbility.getAbility());

        assertNotNull(pokemonAbility.id);
    }

}