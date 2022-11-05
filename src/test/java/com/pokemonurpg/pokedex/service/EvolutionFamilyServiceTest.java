package com.pokemonurpg.pokedex.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvolutionFamilyServiceTest {

    @InjectMocks
    private EvolutionFamilyService evolutionFamilyService;

    @Mock
    private SpeciesService speciesService;

    @Test
    public void getBasicSpeciesReturnsBasic() {
        // Given a species with a preEvolution (1) with a preEvolution (2)
        Species secondStage = new Species();
        Species firstStage = new Species();
        Species basic = new Species();

        secondStage.setPreEvolution(firstStage);
        firstStage.setPreEvolution(basic);

        Species result = evolutionFamilyService.getBasicSpecies(secondStage);
        assertEquals(basic, result);
    }

    @Test
    public void findEvolutionFamilyBySpecies() {
        // Given a basic Species
        Species basic = new Species();

        // Given a Species that evolves from the basic
        Species firstStage = new Species();
        firstStage.setPreEvolution(basic);
        when(speciesService.findByPreEvolution(basic)).thenReturn(Collections.singletonList(firstStage));

        // Given a Species that evolves from the firstStage
        Species secondStage = new Species();
        secondStage.setPreEvolution(firstStage);
        when(speciesService.findByPreEvolution(firstStage)).thenReturn(Collections.singletonList(secondStage));

        // When I call findBySpecies()
        List<List<Species>> evolutionFamily = evolutionFamilyService.findBySpecies(basic);

        // Then I will receive a List of three Lists, which each have one element
        assertEquals(basic, evolutionFamily.get(0).get(0));
        assertEquals(firstStage, evolutionFamily.get(1).get(0));
        assertEquals(secondStage, evolutionFamily.get(2).get(0));

    }

}