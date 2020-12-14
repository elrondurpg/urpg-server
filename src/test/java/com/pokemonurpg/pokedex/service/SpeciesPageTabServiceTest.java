package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpeciesPageTabServiceTest {
    private final static Integer MAX_DEXNO = 500;

    @InjectMocks
    private SpeciesPageTabService speciesPageTabService;

    @Mock
    private SpeciesService speciesService;

    @Test
    public void getNextDexBySpecies() {
        Species species = new Species();
        species.setDexno(MAX_DEXNO);
        when(speciesService.findMaxDexno()).thenReturn(MAX_DEXNO);

        Species species2 = new Species();
        when(speciesService.findFirstByDexno(1)).thenReturn(species2);

        Species result = speciesPageTabService.findNextDexBySpecies(species);
        assertEquals(species2, result);
    }

    @Test
    public void getPrevDexBySpecies() {
        Species species = new Species();
        species.setDexno(1);
        when(speciesService.findMaxDexno()).thenReturn(MAX_DEXNO);

        Species species2 = new Species();
        when(speciesService.findFirstByDexno(MAX_DEXNO)).thenReturn(species2);

        Species result = speciesPageTabService.findPrevDexBySpecies(species);
        assertEquals(species2, result);
    }

}