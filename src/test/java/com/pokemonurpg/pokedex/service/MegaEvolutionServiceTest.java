package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.pokedex.output.MegaEvolutionDto;
import com.pokemonurpg.pokedex.output.TypeMatchupDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MegaEvolutionServiceTest {

    @InjectMocks
    private MegaEvolutionService megaEvolutionService;

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private TypeMatchupService typeMatchupService;

    @Test
    public void findBySpeciesReturnsList() {
        // Given a Species
        Species preMega = new Species();

        // Given a Species that is the mega-evolution of that species
        Species mega = new Species();
        mega.setPreMega(mega);
        when(speciesRepository.findByPreMega(preMega)).thenReturn(Collections.singletonList(mega));

        // Given a set of type matchups
        List<TypeMatchupDto> typeMatchups = new ArrayList<>();
        when(typeMatchupService.findBySpecies(mega)).thenReturn(typeMatchups);

        List<MegaEvolutionDto> megaEvolutions = megaEvolutionService.findBySpecies(preMega);
        assertEquals(mega, megaEvolutions.get(0).getSpecies());
        assertEquals(typeMatchups, megaEvolutions.get(0).getTypeMatchups());
    }

    @Test
    public void findBySpeciesReturnsEmptyList() {
        Species species = new Species();

        when(speciesRepository.findByPreMega(species)).thenReturn(Collections.emptyList());

        List<MegaEvolutionDto> megaEvolutions = megaEvolutionService.findBySpecies(species);
        assertNotNull(megaEvolutions);
    }

}