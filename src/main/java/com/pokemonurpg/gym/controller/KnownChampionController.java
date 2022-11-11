package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.service.KnownChampionService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownChampion")
@CrossOrigin
@Validated
public class KnownChampionController {

    @Resource
    private KnownChampionService knownChampionService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownChampionService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    KnownChampion findByName(@PathVariable("name") String name) {
        return knownChampionService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    KnownChampion create(@Valid @RequestBody KnownChampionInputDto input) {
        return knownChampionService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    KnownChampion update(@Valid @RequestBody KnownChampionInputDto input, @PathVariable int dbid) {
        return knownChampionService.update(input, dbid);
    }
}
