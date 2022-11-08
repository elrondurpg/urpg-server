package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews.V1.Pokemon.Species.Brief;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews.V1.Pokemon.Species.Full;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews.V1.Pokemon.Species.Id;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesGetParams;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/pokemon/species")
@CrossOrigin
@Validated
public class SpeciesController extends NamedConfigurationController<Species, SpeciesGetParams, SpeciesInputDto> {

    public SpeciesController(SpeciesService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(Id.class)
            .withBriefViewClass(Brief.class)
            .withFullViewClass(Full.class)
            .withResourceName("Species")
            .build(), service);
    }
}
