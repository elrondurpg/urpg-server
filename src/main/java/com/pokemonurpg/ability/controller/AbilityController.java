package com.pokemonurpg.ability.controller;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.input.AbilityInputDto;
import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.ability.service.AbilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private Logger logger = LogManager.getLogger(AbilityController.class);

    @Resource
    private AbilityService abilityService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return abilityService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Ability findByName(@PathVariable("name") String name) {
        return abilityService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Ability")
    @PostMapping
    public @ResponseBody
    Ability create(@Valid @RequestBody AbilityInputDto input) {
        return abilityService.create(input);
    }

    @Authorized(permission = "Write Ability")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Ability update(@Valid @RequestBody AbilityInputDto input, @PathVariable int dbid) {
        return abilityService.update(input, dbid);
    }
}
