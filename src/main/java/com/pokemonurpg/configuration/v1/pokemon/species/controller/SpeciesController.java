package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokemonurpg.configuration.v1.config.ConfigurationPageMapper;
import com.pokemonurpg.configuration.v1.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.controller.ConfigurationController;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesGetParams;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews.V1.Pokemon.Species.Brief;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews.V1.Pokemon.Species.Full;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews.V1.Pokemon.Species.Id;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthenticated;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/configuration/v1/pokemon/species")
@CrossOrigin
@Validated
public class SpeciesController extends ConfigurationController<Species, SpeciesGetParams, SpeciesService> {

    public SpeciesController() {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(Id.class)
            .withBriefViewClass(Brief.class)
            .withFullViewClass(Full.class)
            .withResourceName("Species")
            .build());
    }

    @AllowAll
    @GetMapping(path="/{name}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Species.class })
    public @ResponseBody
    Species findByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Species")
    @PostMapping
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Species.class })
    public @ResponseBody
    Species create(@Valid @RequestBody SpeciesInputDto input) {
        return service.create(input);
    }

    @AllowAuthorized(permission = "Write Species")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Species.class })
    public @ResponseBody
    Species update(@Valid @RequestBody SpeciesInputDto input, @PathVariable int dbid) {
        return service.update(input, dbid);
    }
}
