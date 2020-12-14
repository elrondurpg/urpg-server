package com.pokemonurpg.stats.controller;

import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnedPokemonControllerTest {
    private final static Integer DBID = 3432;
    private final static OwnedPokemon POKEMON = mock(OwnedPokemon.class);

    @InjectMocks
    private OwnedPokemonController ownedPokemonController;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void findByDbid() {
        when(ownedPokemonService.findByDbid(DBID)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.findByDbid(DBID));
    }

    @Test
    public void create() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        when(ownedPokemonService.create(input)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.create(input));
    }

    @Test
    public void update() {
        OwnedPokemonInputDto input = new OwnedPokemonInputDto();
        when(ownedPokemonService.update(input, DBID)).thenReturn(POKEMON);
        assertEquals(POKEMON, ownedPokemonController.update(input, DBID));
    }
}