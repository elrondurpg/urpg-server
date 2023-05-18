package com.pokemonurpg.pokedex.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {
    private final static PokemonResponse ENTRY = new PokemonResponse();
    private final static String NAME = "NAME";

    @InjectMocks
    private PokemonController pokemonController;

    @Mock
    private PokedexService pokedexService;

    @Test
    public void findByName() {
        when(pokedexService.findByName(NAME)).thenReturn(ENTRY);
        assertEquals(ENTRY, pokemonController.findByName(NAME));
    }
}