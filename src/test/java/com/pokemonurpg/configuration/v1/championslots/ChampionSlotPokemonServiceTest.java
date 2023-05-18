package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.entities.v1.ChampionSlot;
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
public class ChampionSlotPokemonServiceTest {
    private final static OwnedPokemon OWNED_POKEMON1 = mock(OwnedPokemon.class);
    private final static OwnedPokemon OWNED_POKEMON2 = mock(OwnedPokemon.class);
    private final static Integer OWNED_POKEMON_DBID1 = 432;
    private final static Integer OWNED_POKEMON_DBID2 = 234;

    @InjectMocks
    private ChampionPokemonService championPokemonService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void updateAll() {
        ChampionPokemonRequest input1 = new ChampionPokemonRequest();
        input1.setDbid(OWNED_POKEMON_DBID1);
        input1.setDelete(false);

        ChampionPokemonRequest input2 = new ChampionPokemonRequest();
        input2.setDbid(OWNED_POKEMON_DBID2);
        input2.setDelete(true);

        ChampionSlotRequest input = new ChampionSlotRequest();
        input.setPokemon(Arrays.asList(input1, input2));

        ChampionSlot championSlot = new ChampionSlot();
        Set<OwnedPokemon> pokemons = new HashSet<>();
        pokemons.add(OWNED_POKEMON2);
        championSlot.setPokemon(pokemons);

        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID1)).thenReturn(OWNED_POKEMON1);
        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID2)).thenReturn(OWNED_POKEMON2);

        championPokemonService.updateAll(input, championSlot);

        assertTrue(championSlot.getPokemon().contains(OWNED_POKEMON1));
        assertFalse(championSlot.getPokemon().contains(OWNED_POKEMON2));

    }

}