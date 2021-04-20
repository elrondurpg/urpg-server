package com.pokemonurpg.stats.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ownedPokemon")
@CrossOrigin
@Validated
public class OwnedPokemonController {

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @GetMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon findByDbid(@PathVariable("dbid") Integer dbid) {
        return ownedPokemonService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Member")
    @PostMapping
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon create(@Valid @RequestBody OwnedPokemonInputDto input) {
        return ownedPokemonService.create(input);
    }

    @Authorized(permission = "Write Member")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon update(@Valid @RequestBody OwnedPokemonInputDto input, @PathVariable int dbid) {
        return ownedPokemonService.update(input, dbid);
    }
}
