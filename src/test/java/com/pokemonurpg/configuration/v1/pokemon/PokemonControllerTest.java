package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.entities.v1.Pokemon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PokemonControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private PokemonController pokemonController;

    @Mock
    private PokemonService pokemonService;

    private Pokemon pokemon = new Pokemon();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(pokemonService.findAllNames(false)).thenReturn(names);
        assertEquals(names, pokemonController.findAllNames(false));
    }

    @Test
    public void findByName() {
        when(pokemonService.findByName(NAME)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonController.findByName(NAME));
    }

    @Test
    public void create() {
        PokemonRequest input = new PokemonRequest();
        input.setName(NAME);
        when(pokemonService.create(input)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonController.create(input));
    }

    @Test
    public void update() {
        PokemonRequest input = new PokemonRequest();
        input.setName(NAME);
        when(pokemonService.update(input, DBID)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonController.update(input, DBID));
    }

}