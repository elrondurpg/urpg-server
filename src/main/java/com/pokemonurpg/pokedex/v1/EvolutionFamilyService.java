package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EvolutionFamilyService {

    @Resource
    private PokemonService pokemonService;

    public List<List<Pokemon>> findBySpecies(Pokemon pokemon) {
        List<List<Pokemon>> evolutionFamily = new ArrayList<>();

        Pokemon basic = getBasicSpecies(pokemon);
        evolutionFamily.add(Collections.singletonList(basic));

        List<Pokemon> stageOne = pokemonService.findByPreEvolution(basic);
        evolutionFamily.add(stageOne);

        List<Pokemon> stageTwo = new ArrayList<>();
        stageOne.forEach(firstStageSpecies -> stageTwo.addAll(pokemonService.findByPreEvolution(firstStageSpecies)));
        evolutionFamily.add(stageTwo);

        return evolutionFamily;
    }

    public Pokemon getBasicSpecies(Pokemon pokemon) {
        Pokemon prevo = pokemon.getPreEvolution();
        if (prevo == null) {
            return pokemon;
        }
        else {
            return getBasicSpecies(prevo);
        }
    }
}
