package com.pokemonurpg.configuration.v1.pokemon.ability.controller;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.core.validation.ObjectCreation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration/v1/pokemon/ability/")
@CrossOrigin
@Validated
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return abilityService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.class })
    public @ResponseBody
    Ability findByName(@PathVariable("name") String name) {
        return abilityService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Ability")
    @PostMapping
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.class })
    public @ResponseBody
    Ability create(@Valid @RequestBody AbilityInputDto input) {
        return abilityService.create(input);
    }

    @AllowAuthorized(permission = "Write Ability")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Ability.class })
    public @ResponseBody
    Ability update(@Valid @RequestBody AbilityInputDto input, @PathVariable int dbid) {
        return abilityService.update(input, dbid);
    }
}
