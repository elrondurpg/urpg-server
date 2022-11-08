package com.pokemonurpg.pokedex.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;

import javax.annotation.Resource;

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
