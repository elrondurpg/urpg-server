package com.pokemonurpg.configuration.v1.gymleaders;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.KnownGymLeader;
import com.pokemonurpg.login.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/knownGymLeader")
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
