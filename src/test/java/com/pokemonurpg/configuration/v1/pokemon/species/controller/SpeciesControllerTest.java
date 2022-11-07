package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.pokemonurpg.configuration.v1.lib.config.ConfigurationPageMapper;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

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
        //List<String> names = new ArrayList<>();
        //when(speciesService.find(Matchers.any())).thenReturn(new PageImp);
        //assertEquals(names, speciesController.findAllNames(false, false));
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