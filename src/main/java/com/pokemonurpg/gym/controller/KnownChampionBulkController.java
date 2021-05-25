package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.KnownChampionBulkInputDto;
import com.pokemonurpg.gym.service.KnownChampionBulkService;
import com.pokemonurpg.gym.service.KnownChampionService;
import com.pokemonurpg.security.annotation.Authorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownChampion")
@CrossOrigin
@Validated
public class KnownChampionBulkController {

    @Resource
    private KnownChampionService knownChampionService;

    @Resource
    private KnownChampionBulkService knownChampionBulkService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownChampionService.findAllNames();
    }

    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    List<String> update(@Valid @RequestBody KnownChampionBulkInputDto input) {
        return knownChampionBulkService.update(input);
    }
}
