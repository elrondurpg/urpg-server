package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestTypeInputDto;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.contest.service.ContestTypeService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contestType")
@CrossOrigin
@Validated
public class ContestTypeController {

    @Resource
    private ContestTypeService contestTypeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestTypeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestType findByName(@PathVariable("name") String name) {
        return contestTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestType create(@Valid @RequestBody ContestTypeInputDto input) {
        return contestTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestType update(@Valid @RequestBody ContestTypeInputDto input, @PathVariable int dbid) {
        return contestTypeService.update(input, dbid);
    }
}
