package com.pokemonurpg.configuration.v1.pokemon.species.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SpeciesAttackKeyTest {
    private final static Integer SPECIES_DBID = 32432;
    private final static Integer ATTACK_DBID = 23422;

    @Test
    public void testConstructor() {
        SpeciesAttackKey speciesAttackKey = new SpeciesAttackKey();
        assertNotNull(speciesAttackKey);
    }

    @Test
    public void testPojo() {
        SpeciesAttackKey speciesAttackKey = new SpeciesAttackKey(SPECIES_DBID, ATTACK_DBID);
        assertEquals(SPECIES_DBID, speciesAttackKey.getSpeciesDbid());
        assertEquals(ATTACK_DBID, speciesAttackKey.getAttackDbid());
    }

    @Test
    public void testEqualsAndHashCode() {
        SpeciesAttackKey key1 = new SpeciesAttackKey(SPECIES_DBID, ATTACK_DBID);
        SpeciesAttackKey key2 = new SpeciesAttackKey(SPECIES_DBID, ATTACK_DBID);

        assertEquals(key1, key1);

        assertNotEquals(key1, new Object());

        assertEquals(key1, key2);

        assertEquals(Objects.hash(SPECIES_DBID, ATTACK_DBID), key1.hashCode());
    }

}