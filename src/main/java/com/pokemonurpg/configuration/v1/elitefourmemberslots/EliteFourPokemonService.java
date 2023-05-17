package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.entities.EliteFour;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

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
