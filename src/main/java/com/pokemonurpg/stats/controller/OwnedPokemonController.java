package com.pokemonurpg.stats.controller;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_DELETE;
import static com.pokemonurpg.strings.PermissionNames.WRITE_MEMBER_PERMISSION;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.entities.v1.stats.OwnedPokemon;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthenticated;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.security.annotation.AllowTheOwner;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.SessionService;
import com.pokemonurpg.stats.input.OwnedPokemonInputDto;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import com.pokemonurpg.strings.ErrorStrings;

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

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    @JsonView(value = { View.MemberView.Summary.class })
    public @ResponseBody
    List<OwnedPokemon> findAllNamesByMember(@RequestParam(required = true) String owner) {
        return ownedPokemonService.findByOwner(owner);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
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
}
