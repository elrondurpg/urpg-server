package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
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
    private PokemonService pokemonService;

    @Mock
    private TypeMatchupService typeMatchupService;

    @Test
    public void findBySpeciesReturnsList() {
        // Given a Species
        Pokemon preMega = new Pokemon();

        // Given a Species that is the mega-evolution of that species
        Pokemon mega = new Pokemon();
        mega.setPreMega(mega);
        when(pokemonService.findByPreMega(preMega)).thenReturn(Collections.singletonList(mega));

        // Given a set of type matchups
        List<TypeMatchupResponse> typeMatchups = new ArrayList<>();
        when(typeMatchupService.findBySpecies(mega)).thenReturn(typeMatchups);

        List<MegaEvolutionResponse> megaEvolutions = megaEvolutionService.findBySpecies(preMega);
        assertEquals(mega, megaEvolutions.get(0).getSpecies());
        assertEquals(typeMatchups, megaEvolutions.get(0).getTypeMatchups());
    }

    @Test
    public void findBySpeciesReturnsEmptyList() {
        Pokemon pokemon = new Pokemon();

        when(pokemonService.findByPreMega(pokemon)).thenReturn(Collections.emptyList());

        List<MegaEvolutionResponse> megaEvolutions = megaEvolutionService.findBySpecies(pokemon);
        assertNotNull(megaEvolutions);
    }

}