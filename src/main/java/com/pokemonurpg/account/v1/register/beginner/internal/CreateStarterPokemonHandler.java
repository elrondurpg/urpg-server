package com.pokemonurpg.account.v1.register.beginner.internal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;

@Service
public class CreateStarterPokemonHandler {

    @Resource
    private SpeciesService speciesService;

    @Resource
    private OwnedPokemonRepository ownedPokemonRepository;

    @Resource
    private CaptureMethodService captureMethodService;
    
    public CreateStarterPokemonResponse handle(Member member, CreateStarterPokemonRequest dto) {
        Species species = speciesService.findByName(dto.getSpecies());
        OwnedPokemon pokemon = new OwnedPokemon(member, species, dto.getGender());
        pokemon.setObtained(captureMethodService.findByName("Starter"));
        ownedPokemonRepository.save(pokemon);
        return CreateStarterPokemonResponse.builder()
            .species(species.getName())
            .gender(dto.getGender())
            .build();
    }
}
