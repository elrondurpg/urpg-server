package com.pokemonurpg.configuration.v1.contest.rank.controller;

import com.pokemonurpg.configuration.v1.contest.models.ContestRank;
import com.pokemonurpg.configuration.v1.contest.input.ContestRankInputDto;
import com.pokemonurpg.configuration.v1.contest.service.ContestRankService;
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
@RequestMapping("/contestRank")
@CrossOrigin
@Validated
public class ContestRankController {

    @Resource
    private ContestRankService contestRankService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestRankService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ContestRank findByName(@PathVariable("name") String name) {
        return contestRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    ContestRank create(@Valid @RequestBody ContestRankInputDto input) {
        return contestRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestRank update(@Valid @RequestBody ContestRankInputDto input, @PathVariable int dbid) {
        return contestRankService.update(input, dbid);
    }
}
