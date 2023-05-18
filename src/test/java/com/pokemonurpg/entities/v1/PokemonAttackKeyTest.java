package com.pokemonurpg.entities.v1;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class PokemonAttackKeyTest {
    private final static Integer SPECIES_DBID = 32432;
    private final static Integer ATTACK_DBID = 23422;

    @Test
    public void testConstructor() {
        PokemonAttackKey pokemonAttackKey = new PokemonAttackKey();
        assertNotNull(pokemonAttackKey);
    }

    @Test
    public void testPojo() {
        PokemonAttackKey pokemonAttackKey = new PokemonAttackKey(SPECIES_DBID, ATTACK_DBID);
        assertEquals(SPECIES_DBID, pokemonAttackKey.getPokemonDbid());
        assertEquals(ATTACK_DBID, pokemonAttackKey.getAttackDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        PokemonAttackKey key1 = new PokemonAttackKey(SPECIES_DBID, ATTACK_DBID);
        PokemonAttackKey key2 = new PokemonAttackKey(SPECIES_DBID, ATTACK_DBID);

        assertEquals(key1, key1);

        assertNotEquals(key1, new Object());

        assertEquals(key1, key2);

        assertEquals(Objects.hash(SPECIES_DBID, ATTACK_DBID), key1.hashCode());
    }

}