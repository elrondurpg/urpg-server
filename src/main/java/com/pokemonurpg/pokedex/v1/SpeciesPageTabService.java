package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpeciesPageTabService {

    @Resource
    private PokemonService pokemonService;

    public Pokemon findPrevDexBySpecies(Pokemon pokemon) {
        int dexno = pokemon.getDexno();
        int prevDex = getPrevDex(dexno);
        return pokemonService.findFirstByDexno(prevDex);
    }

    public Pokemon findNextDexBySpecies(Pokemon pokemon) {
        int dexno = pokemon.getDexno();
        int nextDex = getNextDex(dexno);
        return pokemonService.findFirstByDexno(nextDex);
    }

    public int getNextDex(int dexno) {
        int maxDex = pokemonService.findMaxDexno();
        return dexno % maxDex + 1;
    }

    public int getPrevDex(int dexno) {
        int maxDex = pokemonService.findMaxDexno();
        return (dexno + maxDex - 2) % maxDex + 1;
    }

}
