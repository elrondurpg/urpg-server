package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EvolutionFamilyService {

    @Resource
    private SpeciesService speciesService;

    public List<List<Species>> findBySpecies(Species species) {
        List<List<Species>> evolutionFamily = new ArrayList<>();

        Species basic = getBasicSpecies(species);
        evolutionFamily.add(Collections.singletonList(basic));

        List<Species> stageOne = speciesService.findByPreEvolution(basic);
        evolutionFamily.add(stageOne);

        List<Species> stageTwo = new ArrayList<>();
        stageOne.forEach(firstStageSpecies -> stageTwo.addAll(speciesService.findByPreEvolution(firstStageSpecies)));
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