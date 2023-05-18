package com.pokemonurpg.configuration.v1.abilities;

import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ability")
@CrossOrigin
@Validated
public class AbilityController {
    @Resource
    private AbilityService abilityService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return abilityService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Ability findByName(@PathVariable("name") String name) {
        return abilityService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Ability")
    @PostMapping
    public @ResponseBody
    Ability create(@Valid @RequestBody AbilityInputDto input) {
        return abilityService.create(input);
    }

    @AllowAuthorized(permission = "Write Ability")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Ability update(@Valid @RequestBody AbilityInputDto input, @PathVariable int dbid) {
        return abilityService.update(input, dbid);
    }
}
