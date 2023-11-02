package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.security.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/contestAttribute")
@CrossOrigin
@Validated
public class ContestAttributeController {

    @Resource
    private ContestAttributeService contestAttributeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestAttributeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestAttribute findByName(@PathVariable("name") String name) {
        return contestAttributeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestAttribute create(@Valid @RequestBody ContestAttributeInputDto input) {
        return contestAttributeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestAttribute update(@Valid @RequestBody ContestAttributeInputDto input, @PathVariable int dbid) {
        return contestAttributeService.update(input, dbid);
    }
}
