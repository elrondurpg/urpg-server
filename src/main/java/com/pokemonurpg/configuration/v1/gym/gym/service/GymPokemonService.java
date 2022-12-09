package com.pokemonurpg.configuration.v1.gym.gym.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.gym.gym.input.GymInputDto;
import com.pokemonurpg.configuration.v1.gym.gym.input.GymPokemonInputDto;
import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

@Service
public class GymPokemonService {

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public void updateAll(GymInputDto input, Gym gym) {
        Set<OwnedPokemon> pokemons = gym.getPokemon();

        for (GymPokemonInputDto pokemon : input.getPokemon()) {
            Integer dbid = pokemon.getDbid();
            OwnedPokemon ownedPokemon = ownedPokemonService.findByDbid(dbid);
            if (pokemon.getDelete()) {
                pokemons.remove(ownedPokemon);
            }
            else
                pokemons.add(ownedPokemon);
        }
    }
}
