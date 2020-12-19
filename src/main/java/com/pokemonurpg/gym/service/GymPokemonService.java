package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.gym.input.GymPokemonInputDto;
import com.pokemonurpg.gym.models.Gym;
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
