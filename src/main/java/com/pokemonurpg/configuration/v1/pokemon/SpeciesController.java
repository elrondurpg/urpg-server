package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.entities.Species;
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
    List<String> findAllNames(@RequestParam(required = false) Boolean ownable) {
        return speciesService.findAllNames(ownable);
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
