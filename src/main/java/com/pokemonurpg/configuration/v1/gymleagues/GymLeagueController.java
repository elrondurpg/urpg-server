package com.pokemonurpg.configuration.v1.gymleagues;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.login.annotation.AllowAll;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return gymLeagueService.findAllNames();
    }

    @AllowAll
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