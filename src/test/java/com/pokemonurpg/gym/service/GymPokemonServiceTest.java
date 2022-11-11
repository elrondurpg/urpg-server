package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.input.GymPokemonInputDto;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GymPokemonServiceTest {
    private final static OwnedPokemon OWNED_POKEMON1 = mock(OwnedPokemon.class);
    private final static OwnedPokemon OWNED_POKEMON2 = mock(OwnedPokemon.class);
    private final static Integer OWNED_POKEMON_DBID1 = 432;
    private final static Integer OWNED_POKEMON_DBID2 = 234;

    @InjectMocks
    private GymPokemonService gymPokemonService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void updateAll() {
        GymPokemonInputDto input1 = new GymPokemonInputDto();
        input1.setDbid(OWNED_POKEMON_DBID1);
        input1.setDelete(false);

        GymPokemonInputDto input2 = new GymPokemonInputDto();
        input2.setDbid(OWNED_POKEMON_DBID2);
        input2.setDelete(true);

        GymInputDto input = new GymInputDto();
        input.setPokemon(Arrays.asList(input1, input2));

        Gym gym = new Gym();
        Set<OwnedPokemon> pokemons = new HashSet<>();
        pokemons.add(OWNED_POKEMON2);
        gym.setPokemon(pokemons);

        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID1)).thenReturn(OWNED_POKEMON1);
        when(ownedPokemonService.findByDbid(OWNED_POKEMON_DBID2)).thenReturn(OWNED_POKEMON2);

        gymPokemonService.updateAll(input, gym);

        assertTrue(gym.getPokemon().contains(OWNED_POKEMON1));
        assertFalse(gym.getPokemon().contains(OWNED_POKEMON2));

    }

}