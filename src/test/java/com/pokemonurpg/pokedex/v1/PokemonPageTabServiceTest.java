package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokemonPageTabServiceTest {
    private final static Integer MAX_DEXNO = 500;

    @InjectMocks
    private SpeciesPageTabService speciesPageTabService;

    @Mock
    private PokemonService pokemonService;

    @Test
    public void getNextDexBySpecies() {
        Pokemon pokemon = new Pokemon();
        pokemon.setDexno(MAX_DEXNO);
        when(pokemonService.findMaxDexno()).thenReturn(MAX_DEXNO);

        Pokemon pokemon2 = new Pokemon();
        when(pokemonService.findFirstByDexno(1)).thenReturn(pokemon2);

        Pokemon result = speciesPageTabService.findNextDexBySpecies(pokemon);
        assertEquals(pokemon2, result);
    }

    @Test
    public void getPrevDexBySpecies() {
        Pokemon pokemon = new Pokemon();
        pokemon.setDexno(1);
        when(pokemonService.findMaxDexno()).thenReturn(MAX_DEXNO);

        Pokemon pokemon2 = new Pokemon();
        when(pokemonService.findFirstByDexno(MAX_DEXNO)).thenReturn(pokemon2);

        Pokemon result = speciesPageTabService.findPrevDexBySpecies(pokemon);
        assertEquals(pokemon2, result);
    }

}