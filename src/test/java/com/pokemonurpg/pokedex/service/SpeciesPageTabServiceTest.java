package com.pokemonurpg.pokedex.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesPageTabServiceTest {
    private final static Integer MAX_DEXNO = 500;

    @InjectMocks
    private SpeciesPageTabService speciesPageTabService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Test
    public void getNextDexBySpecies() {
        Species species = new Species();
        species.setDexno(MAX_DEXNO);
        when(speciesRepository.findMaxDexno()).thenReturn(MAX_DEXNO);

        Species species2 = new Species();
        when(speciesRepository.findFirstByDexno(1)).thenReturn(species2);

        Species result = speciesPageTabService.findNextDexBySpecies(species);
        assertEquals(species2, result);
    }

    @Test
    public void getPrevDexBySpecies() {
        Species species = new Species();
        species.setDexno(1);
        when(speciesRepository.findMaxDexno()).thenReturn(MAX_DEXNO);

        Species species2 = new Species();
        when(speciesRepository.findFirstByDexno(MAX_DEXNO)).thenReturn(species2);

        Species result = speciesPageTabService.findPrevDexBySpecies(species);
        assertEquals(species2, result);
    }

}