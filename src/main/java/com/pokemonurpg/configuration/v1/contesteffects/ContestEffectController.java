package com.pokemonurpg.configuration.v1.contesteffects;

import com.pokemonurpg.entities.v1.ORASContestEffect;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/contest-effects")
@CrossOrigin
@Validated
public class ContestEffectController {

    @Resource
    private ContestEffectService contestEffectService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestEffectService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ORASContestEffect findByName(@PathVariable("name") String name) {
        return contestEffectService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ORASContestEffect create(@Valid @RequestBody ContestEffectRequest input) {
        return contestEffectService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ORASContestEffect update(@Valid @RequestBody ContestEffectRequest input, @PathVariable int dbid) {
        return contestEffectService.update(input, dbid);
    }
}
