package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestAttributeInputDto;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
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

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestAttributeService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
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
