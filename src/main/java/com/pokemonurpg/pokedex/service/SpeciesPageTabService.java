package com.pokemonurpg.pokedex.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

import javax.annotation.Resource;

@Service
public class SpeciesPageTabService {

    @Resource
    private SpeciesService speciesService;

    public Species findPrevDexBySpecies(Species species) {
        int dexno = species.getDexno();
        int prevDex = getPrevDex(dexno);
        return speciesService.findFirstByDexno(prevDex);
    }

    public Species findNextDexBySpecies(Species species) {
        int dexno = species.getDexno();
        int nextDex = getNextDex(dexno);
        return speciesService.findFirstByDexno(nextDex);
    }

    public int getNextDex(int dexno) {
        int maxDex = speciesService.findMaxDexno();
        return dexno % maxDex + 1;
    }

    public int getPrevDex(int dexno) {
        int maxDex = speciesService.findMaxDexno();
        return (dexno + maxDex - 2) % maxDex + 1;
    }

}
