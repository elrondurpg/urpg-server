package com.pokemonurpg.configuration.v1.champions;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.KnownChampion;
import com.pokemonurpg.login.annotation.AllowAll;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return knownChampionService.findAllNames();
    }

    @AllowAll
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
