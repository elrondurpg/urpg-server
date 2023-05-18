package com.pokemonurpg.configuration.v1.gymleaders;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.GymLeader;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/gym-leaders")
@CrossOrigin
@Validated
public class GymLeaderController {

    @Resource
    private GymLeaderService gymLeaderService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return gymLeaderService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    GymLeader findByName(@PathVariable("name") String name) {
        return gymLeaderService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    GymLeader create(@Valid @RequestBody GymLeaderRequest input) {
        return gymLeaderService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    GymLeader update(@Valid @RequestBody GymLeaderRequest input, @PathVariable int dbid) {
        return gymLeaderService.update(input, dbid);
    }
}
