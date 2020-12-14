package com.pokemonurpg.gym.controller;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.gym.input.BadgeInputDto;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.service.BadgeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/badge")
@CrossOrigin
@Validated
public class BadgeController {

    @Resource
    private BadgeService badgeService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return badgeService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Badge findByName(@PathVariable("name") String name) {
        return badgeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Gym")
    @PostMapping
    public @ResponseBody
    Badge create(@Valid @RequestBody BadgeInputDto input) {
        return badgeService.create(input);
    }

    @Authorized(permission = "Write Gym")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Badge update(@Valid @RequestBody BadgeInputDto input, @PathVariable int dbid) {
        return badgeService.update(input, dbid);
    }
}
