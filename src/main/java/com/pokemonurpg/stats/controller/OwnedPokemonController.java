package com.pokemonurpg.stats.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.security.annotation.AllowAuthenticated;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowTheOwner;
import com.pokemonurpg.stats.input.OwnedPokemonCreateForMemberInputDto;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.input.StarterPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;

@RestController
@RequestMapping("/ownedPokemon")
@CrossOrigin
@Validated
public class OwnedPokemonController {

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @AllowAll
    @GetMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon findByDbid(@PathVariable("dbid") Integer dbid) {
        return ownedPokemonService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthenticated
    @PostMapping("/starter")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon createStarter(@Valid @RequestBody StarterPokemonInputDto input) {
        return ownedPokemonService.createStarter(input);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthenticated
    @PostMapping("/forMe")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon create(@Valid @RequestBody OwnedPokemonInputDto input) {
        return ownedPokemonService.create(input);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Member")
    @PostMapping("/forOther")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon create(@Valid @RequestBody OwnedPokemonCreateForMemberInputDto input) {
        return ownedPokemonService.create(input);
    }

    @AllowTheOwner(type = OwnedPokemon.class)
    @AllowAuthorized(permission = "Write Member")
    @PutMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon update(@Valid @RequestBody OwnedPokemonInputDto input, @PathVariable int dbid) {
        return ownedPokemonService.update(input, dbid);
    }

    @Transactional
    @AllowAuthorized(permission = "Delete Member")
    @DeleteMapping(path="/{dbid}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable int dbid) {
        try {
            ownedPokemonService.delete(dbid);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
