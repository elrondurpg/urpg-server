package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.GymLeagueInputDto;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.service.GymLeagueService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gymLeague")
@CrossOrigin
@Validated
public class GymLeagueController {

    @Resource
    private GymLeagueService gymLeagueService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return gymLeagueService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    GymLeague findByName(@PathVariable("name") String name) {
        return gymLeagueService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    GymLeague create(@Valid @RequestBody GymLeagueInputDto input) {
        return gymLeagueService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    GymLeague update(@Valid @RequestBody GymLeagueInputDto input, @PathVariable int dbid) {
        return gymLeagueService.update(input, dbid);
    }
}
