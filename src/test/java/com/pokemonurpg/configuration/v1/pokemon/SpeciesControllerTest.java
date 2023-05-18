package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.configuration.v1.pokemon.SpeciesController;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private SpeciesController speciesController;

    @Mock
    private SpeciesService speciesService;

    private Species species = new Species();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(speciesService.findAllNames(false)).thenReturn(names);
        assertEquals(names, speciesController.findAllNames(false));
    }

    @Test
    public void findByName() {
        when(speciesService.findByName(NAME)).thenReturn(species);
        assertEquals(species, speciesController.findByName(NAME));
    }

    @Test
    public void create() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setName(NAME);
        when(speciesService.create(input)).thenReturn(species);
        assertEquals(species, speciesController.create(input));
    }

    @Test
    public void update() {
        SpeciesInputDto input = new SpeciesInputDto();
        input.setName(NAME);
        when(speciesService.update(input, DBID)).thenReturn(species);
        assertEquals(species, speciesController.update(input, DBID));
    }

}