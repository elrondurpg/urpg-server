package com.pokemonurpg.species.models;

import com.pokemonurpg.entities.Attack;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesAttackInputDto;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.entities.SpeciesAttack;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpeciesAttackTest {
    private final static Species SPECIES = new Species();
    private final static Attack ATTACK = new Attack();
    private final static String METHOD = "METHOD";
    private final static Integer GENERATION = 32432;

    @Test
    public void testPojo() {
        SpeciesAttack speciesAttack = new SpeciesAttack();
        speciesAttack.setSpecies(SPECIES);
        speciesAttack.setAttack(ATTACK);
        speciesAttack.setMethod(METHOD);
        speciesAttack.setGeneration(GENERATION);

        assertEquals(SPECIES, speciesAttack.getSpecies());
        assertEquals(ATTACK, speciesAttack.getAttack());
        assertEquals(METHOD, speciesAttack.getMethod());
        assertEquals(GENERATION, speciesAttack.getGeneration());
    }

    @Test
    public void testConstructor() {
        SpeciesAttackInputDto input = new SpeciesAttackInputDto();
        input.setMethod(METHOD);
        input.setGeneration(GENERATION);

        SpeciesAttack speciesAttack = new SpeciesAttack(input, SPECIES, ATTACK);
        assertEquals(METHOD, speciesAttack.getMethod());
        assertEquals(GENERATION, speciesAttack.getGeneration());
        assertEquals(SPECIES, speciesAttack.getSpecies());
        assertEquals(ATTACK, speciesAttack.getAttack());
        assertNotNull(speciesAttack.id);
    }

}