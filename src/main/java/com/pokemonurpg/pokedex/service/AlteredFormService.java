package com.pokemonurpg.pokedex.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.SpeciesRepository;
import com.pokemonurpg.pokedex.output.AlteredFormDto;
import com.pokemonurpg.pokedex.util.FormAttackSorter;

@Service
public class AlteredFormService {
    @Resource
    private SpeciesRepository speciesRepository;

    @Resource
    private FormAttackSorter formAttackSorter;

    public List<AlteredFormDto> findBySpecies(Species input) {
        List<AlteredFormDto> alteredForms =
            speciesRepository.findByDexno(input.getDexno())
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
