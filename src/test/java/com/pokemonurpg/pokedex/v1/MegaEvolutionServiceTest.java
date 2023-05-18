package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.pokedex.v1.MegaEvolutionDto;
import com.pokemonurpg.pokedex.v1.MegaEvolutionService;
import com.pokemonurpg.pokedex.v1.TypeMatchupDto;
import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import com.pokemonurpg.pokedex.v1.TypeMatchupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MegaEvolutionServiceTest {

    @InjectMocks
    private MegaEvolutionService megaEvolutionService;

    @Mock
    private SpeciesService speciesService;

    @Mock
    private TypeMatchupService typeMatchupService;

    @Test
    public void findBySpeciesReturnsList() {
        // Given a Species
        Species preMega = new Species();

        // Given a Species that is the mega-evolution of that species
        Species mega = new Species();
        mega.setPreMega(mega);
        when(speciesService.findByPreMega(preMega)).thenReturn(Collections.singletonList(mega));

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

        when(speciesService.findByPreMega(species)).thenReturn(Collections.emptyList());

        List<MegaEvolutionDto> megaEvolutions = megaEvolutionService.findBySpecies(species);
        assertNotNull(megaEvolutions);
    }

}