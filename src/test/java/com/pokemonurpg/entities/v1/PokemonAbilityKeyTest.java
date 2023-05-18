package com.pokemonurpg.entities.v1;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class PokemonAbilityKeyTest {
    private final static Integer SPECIES_DBID = 32432;
    private final static Integer ABILITY_DBID = 23423;

    @Test
    public void testConstructor() {
        PokemonAbilityKey pokemonAbilityKey = new PokemonAbilityKey();
        assertNotNull(pokemonAbilityKey);
    }

    @Test
    public void testPojo() {
        PokemonAbilityKey pokemonAbilityKey = new PokemonAbilityKey(SPECIES_DBID, ABILITY_DBID);
        assertEquals(SPECIES_DBID, pokemonAbilityKey.getPokemonDbid());
        assertEquals(ABILITY_DBID, pokemonAbilityKey.getAbilityDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        PokemonAbilityKey key1 = new PokemonAbilityKey(SPECIES_DBID, ABILITY_DBID);
        PokemonAbilityKey key2 = new PokemonAbilityKey(SPECIES_DBID, ABILITY_DBID);

        assertEquals(key1, key1);

        assertNotEquals(key1, new Object());

        assertEquals(key1, key2);

        assertEquals(Objects.hash(SPECIES_DBID, ABILITY_DBID), key1.hashCode());
    }

}