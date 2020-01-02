package com.pokemonurpg.dto.species;

import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.object.pokemon.Species;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpeciesDtoTest {

    private Species createSpecies() {
        Species species = new Species();
        species.setDbid(1);
        species.setDexno(25);
        species.setName("Pikachu");
        return species;
    }

    @Test
    public void createEmptySpeciesDtoFromNullSpecies() {
        SpeciesDto empty = new SpeciesDto(null);
        assertEquals(0, empty.getDbid());
        assertNull(empty.getType1());
    }

    @Test
    public void createSpeciesDtoFromSpecies() {
        Species species = createSpecies();
        SpeciesDto dto = new SpeciesDto(species);
        assertEquals(species.getDbid(), dto.getDbid());
        assertEquals(species.getDexno(), dto.getDexno());
        assertEquals(species.getName(), dto.getName());
    }

}