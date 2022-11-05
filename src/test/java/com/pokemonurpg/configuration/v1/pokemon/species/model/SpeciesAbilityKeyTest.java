package com.pokemonurpg.configuration.v1.pokemon.species.model;

import org.junit.Test;

import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbilityKey;

import java.util.Objects;

import static org.junit.Assert.*;

public class SpeciesAbilityKeyTest {
    private final static Integer SPECIES_DBID = 32432;
    private final static Integer ABILITY_DBID = 23423;

    @Test
    public void testConstructor() {
        SpeciesAbilityKey speciesAbilityKey = new SpeciesAbilityKey();
        assertNotNull(speciesAbilityKey);
    }

    @Test
    public void testPojo() {
        SpeciesAbilityKey speciesAbilityKey = new SpeciesAbilityKey(SPECIES_DBID, ABILITY_DBID);
        assertEquals(SPECIES_DBID, speciesAbilityKey.getSpeciesDbid());
        assertEquals(ABILITY_DBID, speciesAbilityKey.getAbilityDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        SpeciesAbilityKey key1 = new SpeciesAbilityKey(SPECIES_DBID, ABILITY_DBID);
        SpeciesAbilityKey key2 = new SpeciesAbilityKey(SPECIES_DBID, ABILITY_DBID);

        assertEquals(key1, key1);

        assertNotEquals(key1, new Object());

        assertEquals(key1, key2);

        assertEquals(Objects.hash(SPECIES_DBID, ABILITY_DBID), key1.hashCode());
    }

}