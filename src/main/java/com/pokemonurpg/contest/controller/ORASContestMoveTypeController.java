package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.service.ORASContestMoveTypeService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orasContestMoveType")
@CrossOrigin
@Validated
public class ORASContestMoveTypeController {

    @Resource
    private ORASContestMoveTypeService orasContestMoveTypeService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return orasContestMoveTypeService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ORASContestMoveType findByName(@PathVariable("name") String name) {
        return orasContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ORASContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return orasContestMoveTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ORASContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return orasContestMoveTypeService.update(input, dbid);
    }
}
