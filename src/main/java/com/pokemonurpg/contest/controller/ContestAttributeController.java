package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contestAttribute")
@CrossOrigin
@Validated
public class ContestAttributeController {

    @Resource
    private ContestAttributeService contestAttributeService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestAttributeService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestAttribute findByName(@PathVariable("name") String name) {
        return contestAttributeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestAttribute create(@Valid @RequestBody ContestAttributeInputDto input) {
        return contestAttributeService.create(input);
    }

    @Authorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestAttribute update(@Valid @RequestBody ContestAttributeInputDto input, @PathVariable int dbid) {
        return contestAttributeService.update(input, dbid);
    }
}
