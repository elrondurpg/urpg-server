package com.pokemonurpg.configuration.v1.pokemon.type.controller;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;
import com.pokemonurpg.core.validation.ObjectCreation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration/v1/pokemon/type")
@CrossOrigin
@Validated
public class TypeController {
    @Resource
    private TypeService typeService;

    @GetMapping
	@AllowAll
    public @ResponseBody
    List<String> findAllNames() {
        return typeService.findAllNames();
    }

    @GetMapping(path="/{name}")
	@AllowAll
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Type.class })
    public @ResponseBody
    Type findByName(@PathVariable("name") String name) {
        return typeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Species")
    @PostMapping
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Type.class })
    public @ResponseBody
    Type create(@Valid @RequestBody TypeInputDto input) {
        return typeService.create(input);
    }

    @AllowAuthorized(permission = "Write Species")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { ConfigurationViews.V1.Pokemon.Type.class })
    public @ResponseBody
    Type update(@Valid @RequestBody TypeInputDto input, @PathVariable int dbid) {
        return typeService.update(input, dbid);
    }
}
