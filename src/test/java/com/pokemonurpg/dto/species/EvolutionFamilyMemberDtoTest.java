package com.pokemonurpg.dto.species;

import com.pokemonurpg.dto.species.response.EvolutionFamilyMemberDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.Species;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvolutionFamilyMemberDtoTest {

    public Species pikachu = TestObjectFactory.createPikachu();

    @Test
    public void buildEvolutionFamilyMemberDto() {
        EvolutionFamilyMemberDto dto = new EvolutionFamilyMemberDto(pikachu, TestObjectFactory.TEST_EVOLUTION_METHOD);
        assertEquals(dto.getDbid(), pikachu.getDbid());
        assertEquals(dto.getDisplayName(), pikachu.getDisplayName());
        assertEquals(dto.getName(), pikachu.getName());
        assertEquals(dto.getMethod(), TestObjectFactory.TEST_EVOLUTION_METHOD);
    }

    @Test
    public void buildEvolutionFamilyMemberDtoFromNullSpecies() {
        EvolutionFamilyMemberDto dto = new EvolutionFamilyMemberDto(null, null);
        assertNull(dto.getName());
        assertNull(dto.getMethod());
    }

}