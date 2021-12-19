package com.pokemonurpg.species.controller;

import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.species.input.SpeciesInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/species")
@CrossOrigin
@Validated
public class SpeciesController {

    @Resource
    private SpeciesService speciesService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return speciesService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/starters")
    public @ResponseBody
    List<String> findAllStarterNames() { return speciesService.findAllStarterNames(); }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Species findByName(@PathVariable("name") String name) {
        return speciesService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Species")
    @PostMapping
    public @ResponseBody
    Species create(@Valid @RequestBody SpeciesInputDto input) {
        return speciesService.create(input);
    }

    @AllowAuthorized(permission = "Write Species")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Species update(@Valid @RequestBody SpeciesInputDto input, @PathVariable int dbid) {
        return speciesService.update(input, dbid);
    }
}
