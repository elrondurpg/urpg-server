package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.pokedex.output.AlteredFormDto;
import com.pokemonurpg.pokedex.util.FormAttackSorter;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlteredFormService {
    @Resource
    private SpeciesService speciesService;

    @Resource
    private FormAttackSorter formAttackSorter;

    public List<AlteredFormDto> findBySpecies(Species input) {
        List<AlteredFormDto> alteredForms =
            speciesService.findByDexno(input.getDexno())
            .stream()
            .filter(species -> species.getPreMega() == null)
            .map(AlteredFormDto::new)
            .collect(Collectors.toList());
        if (alteredForms.size() > 1) {
            formAttackSorter.run(alteredForms);
            return alteredForms;
        }
        else return Collections.emptyList();
    }
}
