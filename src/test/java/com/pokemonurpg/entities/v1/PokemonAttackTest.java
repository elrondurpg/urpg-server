package com.pokemonurpg.entities.v1;

import com.pokemonurpg.configuration.v1.pokemon.PokemonAttackRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonAttackTest {
    private final static Pokemon POKEMON = new Pokemon();
    private final static Attack ATTACK = new Attack();
    private final static String METHOD = "METHOD";
    private final static Integer GENERATION = 32432;

    @Test
    public void testPojo() {
        PokemonAttack pokemonAttack = new PokemonAttack();
        pokemonAttack.setSpecies(POKEMON);
        pokemonAttack.setAttack(ATTACK);
        pokemonAttack.setMethod(METHOD);
        pokemonAttack.setGeneration(GENERATION);

        assertEquals(POKEMON, pokemonAttack.getSpecies());
        assertEquals(ATTACK, pokemonAttack.getAttack());
        assertEquals(METHOD, pokemonAttack.getMethod());
        assertEquals(GENERATION, pokemonAttack.getGeneration());
    }

    @Test
    public void testConstructor() {
        PokemonAttackRequest input = new PokemonAttackRequest();
        input.setMethod(METHOD);
        input.setGeneration(GENERATION);

        PokemonAttack pokemonAttack = new PokemonAttack(input, POKEMON, ATTACK);
        assertEquals(METHOD, pokemonAttack.getMethod());
        assertEquals(GENERATION, pokemonAttack.getGeneration());
        assertEquals(POKEMON, pokemonAttack.getSpecies());
        assertEquals(ATTACK, pokemonAttack.getAttack());
        assertNotNull(pokemonAttack.id);
    }

}