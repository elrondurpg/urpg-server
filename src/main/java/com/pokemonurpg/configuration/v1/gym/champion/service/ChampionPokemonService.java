package com.pokemonurpg.configuration.v1.gym.champion.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.gym.champion.input.ChampionInputDto;
import com.pokemonurpg.configuration.v1.gym.champion.input.ChampionPokemonInputDto;
import com.pokemonurpg.entities.v1.gym.Champion;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;

@Service
public class ChampionPokemonService {

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public void updateAll(ChampionInputDto input, Champion champion) {
        Set<OwnedPokemon> pokemons = champion.getPokemon();

        for (ChampionPokemonInputDto pokemon : input.getPokemon()) {
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
