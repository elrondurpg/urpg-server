package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MegaEvolutionService {
    @Resource
    private PokemonService pokemonService;

    @Resource
    private TypeMatchupService typeMatchupService;

    public List<MegaEvolutionResponse> findBySpecies(Pokemon pokemon) {
        List<Pokemon> megaSpecies = pokemonService.findByPreMega(pokemon);

        return megaSpecies.stream().map(mega -> {
            MegaEvolutionResponse dto = new MegaEvolutionResponse(mega);
            dto.setTypeMatchups(typeMatchupService.findBySpecies(mega));
            return dto;
        }).collect(Collectors.toList());
    }

}
