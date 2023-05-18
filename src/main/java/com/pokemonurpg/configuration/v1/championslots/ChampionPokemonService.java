package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
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
