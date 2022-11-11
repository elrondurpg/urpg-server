package com.pokemonurpg.pokedex.controller;

import com.pokemonurpg.pokedex.output.PokedexEntryDto;
import com.pokemonurpg.pokedex.service.PokedexService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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