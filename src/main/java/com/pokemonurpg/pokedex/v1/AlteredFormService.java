package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlteredFormService {
    @Resource
    private PokemonService pokemonService;

    @Resource
    private FormAttackSorter formAttackSorter;

    public List<AlteredFormResponse> findBySpecies(Pokemon input) {
        List<AlteredFormResponse> alteredForms =
            pokemonService.findByDexno(input.getDexno())
            .stream()
            .filter(species -> species.getPreMega() == null)
            .map(AlteredFormResponse::new)
            .collect(Collectors.toList());
        if (alteredForms.size() > 1) {
            formAttackSorter.run(alteredForms);
            return alteredForms;
        }
        else return Collections.emptyList();
    }
}
