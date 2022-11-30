package com.pokemonurpg.account.v1.register.newplayer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
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
    
    public void handle(CreateStarterPokemonDto dto) {
        Species species = speciesService.findByName(dto.getInput().getStarter().getSpecies());
        OwnedPokemon pokemon = new OwnedPokemon(dto.getMember(), species, dto.getInput().getStarter().getGender());
        pokemon.setObtained(captureMethodService.findByName("Starter"));
        ownedPokemonRepository.save(pokemon);
    }
}
