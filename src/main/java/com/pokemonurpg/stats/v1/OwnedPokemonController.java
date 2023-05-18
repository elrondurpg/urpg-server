package com.pokemonurpg.stats.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.annotations.AllowAuthenticated;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowTheOwner;
import com.pokemonurpg.login.v1.AuthorizationService;
import com.pokemonurpg.login.v1.SessionService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.lib.v1.strings.ErrorStrings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.inject.Provider;
import javax.validation.Valid;

import static com.pokemonurpg.lib.v1.strings.ErrorStrings.ERROR_ON_DELETE;
import static com.pokemonurpg.lib.v1.strings.PermissionNames.WRITE_MEMBER_PERMISSION;

import java.util.List;

@RestController
@RequestMapping("/urpg-stats/v1/pokemon")
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
    OwnedPokemon create(@Valid @RequestBody OwnedPokemonRequest input) {
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
    OwnedPokemon update(@Valid @RequestBody OwnedPokemonRequest input, @PathVariable int dbid) {
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
