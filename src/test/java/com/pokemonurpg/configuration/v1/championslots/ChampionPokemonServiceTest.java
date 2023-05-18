package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.configuration.v1.championslots.ChampionInputDto;
import com.pokemonurpg.configuration.v1.championslots.ChampionPokemonInputDto;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.configuration.v1.championslots.ChampionPokemonService;
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
public class ChampionPokemonServiceTest {
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
        ChampionPokemonInputDto input1 = new ChampionPokemonInputDto();
        input1.setDbid(OWNED_POKEMON_DBID1);
        input1.setDelete(false);

        ChampionPokemonInputDto input2 = new ChampionPokemonInputDto();
        input2.setDbid(OWNED_POKEMON_DBID2);
        input2.setDelete(true);

        ChampionInputDto input = new ChampionInputDto();
        input.setPokemon(Arrays.asList(input1, input2));

        Champion champion = new Champion();
        Set<OwnedPokemon> pokemons = new HashSet<>();
        pokemons.add(OWNED_POKEMON2);
        champion.setPokemon(pokemons);

        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID1)).thenReturn(OWNED_POKEMON1);
        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID2)).thenReturn(OWNED_POKEMON2);

        championPokemonService.updateAll(input, champion);

        assertTrue(champion.getPokemon().contains(OWNED_POKEMON1));
        assertFalse(champion.getPokemon().contains(OWNED_POKEMON2));

    }

}