package com.pokemonurpg.general.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.general.input.FlagInputDto;
import com.pokemonurpg.general.models.Flag;
import com.pokemonurpg.general.service.FlagService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flag")
@CrossOrigin
@Validated
public class FlagController {

    @Resource
    private FlagService flagService;

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping
    public @ResponseBody
    List<Flag> findAll() {
        return flagService.findAll();
    }

    @AllowAuthorized(permission = "Write Admin")
    @GetMapping(path="/{name}")
    public @ResponseBody
    Flag findByName(@PathVariable("name") String name) {
        return flagService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Admin")
    @PostMapping
    public @ResponseBody
    Flag create(@Valid @RequestBody FlagInputDto input) {
        return flagService.create(input);
    }

    @AllowAuthorized(permission = "Write Admin")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Flag update(@Valid @RequestBody FlagInputDto input, @PathVariable int dbid) {
        return flagService.update(input, dbid);
    }
}
