package com.pokemonurpg.pokedex.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.SpeciesRepository;

@Service
public class SpeciesPageTabService {

    @Resource
    private SpeciesRepository speciesRepository;

    public Species findPrevDexBySpecies(Species species) {
        int dexno = species.getDexno();
        int prevDex = getPrevDex(dexno);
        return speciesRepository.findFirstByDexno(prevDex);
    }

    public Species findNextDexBySpecies(Species species) {
        int dexno = species.getDexno();
        int nextDex = getNextDex(dexno);
        return speciesRepository.findFirstByDexno(nextDex);
    }

    public int getNextDex(int dexno) {
        int maxDex = speciesRepository.findMaxDexno();
        return dexno % maxDex + 1;
    }

    public int getPrevDex(int dexno) {
        int maxDex = speciesRepository.findMaxDexno();
        return (dexno + maxDex - 2) % maxDex + 1;
    }

}
