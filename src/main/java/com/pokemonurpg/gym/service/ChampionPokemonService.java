package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.ChampionInputDto;
import com.pokemonurpg.gym.input.ChampionPokemonInputDto;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

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
