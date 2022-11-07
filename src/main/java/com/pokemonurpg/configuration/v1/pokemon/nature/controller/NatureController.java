package com.pokemonurpg.configuration.v1.pokemon.nature.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;
import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputDto;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration/v1/pokemon/nature")
@CrossOrigin
@Validated
public class NatureController {

    @Resource
    private NatureService natureService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return natureService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Nature.class })
    public @ResponseBody
    Nature findByName(@PathVariable("name") String name) {
        return natureService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write General")
    @PostMapping
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Nature.class })
    public @ResponseBody
    Nature create(@Valid @RequestBody NatureInputDto input) {
        return natureService.create(input);
    }

    @AllowAuthorized(permission = "Write General")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Nature.class })
    public @ResponseBody
    Nature update(@Valid @RequestBody NatureInputDto input, @PathVariable int dbid) {
        return natureService.update(input, dbid);
    }
}
