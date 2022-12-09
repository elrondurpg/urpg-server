package com.pokemonurpg.configuration.v1.gym.elitefour.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourPokemonInputDto;
import com.pokemonurpg.entities.v1.gym.EliteFour;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

@Service
public class EliteFourPokemonService {

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public void updateAll(EliteFourInputDto input, EliteFour eliteFour) {
        Set<OwnedPokemon> pokemons = eliteFour.getPokemon();

        for (EliteFourPokemonInputDto pokemon : input.getPokemon()) {
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
