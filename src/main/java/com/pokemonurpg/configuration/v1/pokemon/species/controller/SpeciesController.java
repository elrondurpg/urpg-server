package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.fasterxml.jackson.annotation.JsonView;
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

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration/v1/pokemon/species")
@CrossOrigin
@Validated
public class SpeciesController extends ConfigurationController<Species, SpeciesGetParams, SpeciesService> {

    protected Class<? extends ConfigurationViews.V1> getIdViewClass() {
        return ConfigurationViews.V1.Pokemon.Species.Id.class;
    }

    protected Class<? extends ConfigurationViews.V1> getBriefViewClass() {
        return ConfigurationViews.V1.Pokemon.Species.Brief.class;
    }

    protected Class<? extends ConfigurationViews.V1> getFullViewClass() {
        return ConfigurationViews.V1.Pokemon.Species.Full.class;
    }

    @AllowAll
    @GetMapping(path="/starters")
    public @ResponseBody
    List<String> findAllStarterNames() { return service.findAllStarterNames(); }

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
