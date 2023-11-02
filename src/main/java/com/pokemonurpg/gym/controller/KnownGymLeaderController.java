package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.service.KnownGymLeaderService;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/knownGymLeader")
@CrossOrigin
@Validated
public class KnownGymLeaderController {

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownGymLeaderService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    KnownGymLeader findByName(@PathVariable("name") String name) {
        return knownGymLeaderService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    KnownGymLeader create(@Valid @RequestBody KnownGymLeaderInputDto input) {
        return knownGymLeaderService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    KnownGymLeader update(@Valid @RequestBody KnownGymLeaderInputDto input, @PathVariable int dbid) {
        return knownGymLeaderService.update(input, dbid);
    }
}
