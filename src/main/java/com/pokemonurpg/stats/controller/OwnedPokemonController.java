package com.pokemonurpg.stats.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.security.annotation.AllowAuthenticated;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowTheOwner;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import com.pokemonurpg.strings.ErrorStrings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.validation.Valid;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;
import static com.pokemonurpg.strings.PermissionNames.WRITE_MEMBER_PERMISSION;

import java.util.List;

@RestController
@RequestMapping("/ownedPokemon")
@CrossOrigin
@Validated
public class OwnedPokemonController {

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private Provider<SessionService> sessionServiceProvider;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    @AllowAll
    @GetMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    List<OwnedPokemon> findAllNamesByMember(@RequestParam(required = true) String owner) {
        return ownedPokemonService.findByOwner(owner);
    }

    @AllowAll
    @GetMapping(path="/{dbid}")
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon findByDbid(@PathVariable("dbid") Integer dbid) {
        return ownedPokemonService.findByDbid(dbid);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthenticated
    @PostMapping
    @JsonView(value = { View.MemberView.Pokemon.class })
    public @ResponseBody
    OwnedPokemon create(@Valid @RequestBody OwnedPokemonInputDto input) {
        String memberName = input.getTrainer();
        if (memberName != null) {
            if (!memberName.equals(sessionServiceProvider.get().getAuthenticatedMember().getName())) {
                if (!authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorStrings.ERROR_POKEMON_FOR_OTHER);
                }
            }
        }
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
    ResponseEntity<?> delete(@PathVariable int dbid) {
        try {
            ownedPokemonService.delete(dbid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_DELETE);
        }
    }
}
