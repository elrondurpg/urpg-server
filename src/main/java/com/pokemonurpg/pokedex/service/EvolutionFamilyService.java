package com.pokemonurpg.pokedex.service;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.SpeciesRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EvolutionFamilyService {

    @Resource
    private SpeciesRepository speciesRepository;

    public List<List<Species>> findBySpecies(Species species) {
        List<List<Species>> evolutionFamily = new ArrayList<>();

        Species basic = getBasicSpecies(species);
        evolutionFamily.add(Collections.singletonList(basic));

        List<Species> stageOne = speciesRepository.findByPreEvolution(basic);
        evolutionFamily.add(stageOne);

        List<Species> stageTwo = new ArrayList<>();
        stageOne.forEach(firstStageSpecies -> stageTwo.addAll(speciesRepository.findByPreEvolution(firstStageSpecies)));
        evolutionFamily.add(stageTwo);

        return evolutionFamily;
    }

    Species getBasicSpecies(Species species) {
        Species prevo = species.getPreEvolution();
        if (prevo == null) {
            return species;
        }
        else {
            return getBasicSpecies(prevo);
        }
    }
}
