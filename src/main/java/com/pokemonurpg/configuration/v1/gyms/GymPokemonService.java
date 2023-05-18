package com.pokemonurpg.configuration.v1.gyms;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
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
