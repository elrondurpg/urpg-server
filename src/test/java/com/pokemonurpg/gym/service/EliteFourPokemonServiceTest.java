package com.pokemonurpg.gym.service;

import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourInputDto;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourPokemonInputDto;
import com.pokemonurpg.entities.v1.EliteFour;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourPokemonService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EliteFourPokemonServiceTest {
    private final static OwnedPokemon OWNED_POKEMON1 = mock(OwnedPokemon.class);
    private final static OwnedPokemon OWNED_POKEMON2 = mock(OwnedPokemon.class);
    private final static Integer OWNED_POKEMON_DBID1 = 432;
    private final static Integer OWNED_POKEMON_DBID2 = 234;

    @InjectMocks
    private EliteFourPokemonService eliteFourPokemonService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void updateAll() {
        EliteFourPokemonInputDto input1 = new EliteFourPokemonInputDto();
        input1.setDbid(OWNED_POKEMON_DBID1);
        input1.setDelete(false);

        EliteFourPokemonInputDto input2 = new EliteFourPokemonInputDto();
        input2.setDbid(OWNED_POKEMON_DBID2);
        input2.setDelete(true);

        EliteFourInputDto input = new EliteFourInputDto();
        input.setPokemon(Arrays.asList(input1, input2));

        EliteFour eliteFour = new EliteFour();
        Set<OwnedPokemon> pokemons = new HashSet<>();
        pokemons.add(OWNED_POKEMON2);
        eliteFour.setPokemon(pokemons);

        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID1)).thenReturn(OWNED_POKEMON1);
        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID2)).thenReturn(OWNED_POKEMON2);

        eliteFourPokemonService.updateAll(input, eliteFour);

        assertTrue(eliteFour.getPokemon().contains(OWNED_POKEMON1));
        assertFalse(eliteFour.getPokemon().contains(OWNED_POKEMON2));

    }

}