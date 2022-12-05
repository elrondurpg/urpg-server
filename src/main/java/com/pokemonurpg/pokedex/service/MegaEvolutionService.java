package com.pokemonurpg.pokedex.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.entities.v1.pokemon.SpeciesRepository;
import com.pokemonurpg.pokedex.output.MegaEvolutionDto;

@Service
public class MegaEvolutionService {
    @Resource
    private SpeciesRepository speciesRepository;

    @Resource
    private TypeMatchupService typeMatchupService;

    public List<MegaEvolutionDto> findBySpecies(Species species) {
        List<Species> megaSpecies = speciesRepository.findByPreMega(species);

        return megaSpecies.stream().map(mega -> {
            MegaEvolutionDto dto = new MegaEvolutionDto(mega);
            dto.setTypeMatchups(typeMatchupService.findBySpecies(mega));
            return dto;
        }).collect(Collectors.toList());
    }

}
