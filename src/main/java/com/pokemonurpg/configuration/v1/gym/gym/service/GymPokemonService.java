package com.pokemonurpg.configuration.v1.gym.gym.service;

import com.pokemonurpg.configuration.v1.gym.gym.input.GymPokemonInputDto;
import com.pokemonurpg.configuration.v1.gym.gym.input.GymInputDto;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

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
