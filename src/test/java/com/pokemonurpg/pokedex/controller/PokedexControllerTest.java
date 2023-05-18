package com.pokemonurpg.pokedex.controller;

import com.pokemonurpg.pokedex.v1.PokedexController;
import com.pokemonurpg.pokedex.v1.PokedexEntryDto;
import com.pokemonurpg.pokedex.v1.PokedexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokedexControllerTest {
    private final static PokedexEntryDto ENTRY = new PokedexEntryDto();
    private final static String NAME = "NAME";

    @InjectMocks
    private PokedexController pokedexController;

    @Mock
    private PokedexService pokedexService;

    @Test
    public void findByName() {
        when(pokedexService.findByName(NAME)).thenReturn(ENTRY);
        assertEquals(ENTRY, pokedexController.findByName(NAME));
    }
}