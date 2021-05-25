package com.pokemonurpg.gym.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.input.KnownGymLeaderBulkInputDto;
import com.pokemonurpg.gym.service.KnownGymLeaderBulkService;
import com.pokemonurpg.gym.service.KnownGymLeaderService;
import com.pokemonurpg.security.annotation.Authorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownGymLeader")
@CrossOrigin
@Validated
public class KnownGymLeaderBulkController {

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    @Resource
    private KnownGymLeaderBulkService knownGymLeaderBulkService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownGymLeaderService.findAllNames();
    }

    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    List<String> update(@Valid @RequestBody KnownGymLeaderBulkInputDto input) {
        return knownGymLeaderBulkService.update(input);
    }
}
