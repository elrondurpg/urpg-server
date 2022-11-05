package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.pokedex.output.AlteredFormDto;
import com.pokemonurpg.pokedex.util.FormAttackSorter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlteredFormServiceTest {
    private final static Integer DEXNO = 3242;

    @InjectMocks
    private AlteredFormService alteredFormService;

    @Mock
    private SpeciesService speciesService;

    @Mock
    private FormAttackSorter formAttackSorter;

    @Test
    public void returnsEmptyListWhenSpeciesHasNoAlternateForms() {
        Species species1 = new Species();
        species1.setDexno(DEXNO);
        when(speciesService.findByDexno(DEXNO)).thenReturn(Collections.singletonList(species1));

        List<AlteredFormDto> alteredForms = alteredFormService.findBySpecies(species1);
        assertEquals(0, alteredForms.size());
    }

    @Test
    public void returnsListOfAlteredForms() {
        Species species1 = new Species();
        species1.setDexno(DEXNO);
        Species species2 = new Species();
        species2.setDexno(DEXNO);
        when(speciesService.findByDexno(DEXNO)).thenReturn(Arrays.asList(species1, species2));

        List<AlteredFormDto> alteredForms = alteredFormService.findBySpecies(species1);
        verify(formAttackSorter, times(1)).run(alteredForms);
        assertEquals(2, alteredForms.size());
    }

    @Test
    public void returnsEmptyListWhenOneOfTwoFormsIsAMega() {
        Species species1 = new Species();
        species1.setDexno(DEXNO);
        Species species2 = new Species();
        species2.setDexno(DEXNO);
        species2.setPreMega(species1);
        when(speciesService.findByDexno(DEXNO)).thenReturn(Arrays.asList(species1, species2));

        List<AlteredFormDto> alteredForms = alteredFormService.findBySpecies(species1);
        assertEquals(0, alteredForms.size());
    }
}