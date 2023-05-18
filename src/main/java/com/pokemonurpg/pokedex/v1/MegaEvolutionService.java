package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MegaEvolutionService {
    @Resource
    private SpeciesService speciesService;

    @Resource
    private TypeMatchupService typeMatchupService;

    public List<MegaEvolutionDto> findBySpecies(Species species) {
        List<Species> megaSpecies = speciesService.findByPreMega(species);

        return megaSpecies.stream().map(mega -> {
            MegaEvolutionDto dto = new MegaEvolutionDto(mega);
            dto.setTypeMatchups(typeMatchupService.findBySpecies(mega));
            return dto;
        }).collect(Collectors.toList());
    }

}
