package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvolutionFamilyServiceTest {

    @InjectMocks
    private EvolutionFamilyService evolutionFamilyService;

    @Mock
    private PokemonService pokemonService;

    @Test
    public void getBasicSpeciesReturnsBasic() {
        // Given a species with a preEvolution (1) with a preEvolution (2)
        Pokemon secondStage = new Pokemon();
        Pokemon firstStage = new Pokemon();
        Pokemon basic = new Pokemon();

        secondStage.setPreEvolution(firstStage);
        firstStage.setPreEvolution(basic);

        Pokemon result = evolutionFamilyService.getBasicSpecies(secondStage);
        assertEquals(basic, result);
    }

    @Test
    public void findEvolutionFamilyBySpecies() {
        // Given a basic Species
        Pokemon basic = new Pokemon();

        // Given a Species that evolves from the basic
        Pokemon firstStage = new Pokemon();
        firstStage.setPreEvolution(basic);
        when(pokemonService.findByPreEvolution(basic)).thenReturn(Collections.singletonList(firstStage));

        // Given a Species that evolves from the firstStage
        Pokemon secondStage = new Pokemon();
        secondStage.setPreEvolution(firstStage);
        when(pokemonService.findByPreEvolution(firstStage)).thenReturn(Collections.singletonList(secondStage));

        // When I call findBySpecies()
        List<List<Pokemon>> evolutionFamily = evolutionFamilyService.findBySpecies(basic);

        // Then I will receive a List of three Lists, which each have one element
        assertEquals(basic, evolutionFamily.get(0).get(0));
        assertEquals(firstStage, evolutionFamily.get(1).get(0));
        assertEquals(secondStage, evolutionFamily.get(2).get(0));

    }

}