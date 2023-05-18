package com.pokemonurpg.configuration.v1.champions;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/champions")
@CrossOrigin
@Validated
public class ChampionController {

    @Resource
    private ChampionService championService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return championService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Champion findByName(@PathVariable("name") String name) {
        return championService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    Champion create(@Valid @RequestBody ChampionRequest input) {
        return championService.create(input);
    }

    @AllowAuthorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Champion update(@Valid @RequestBody ChampionRequest input, @PathVariable int dbid) {
        return championService.update(input, dbid);
    }
}
