package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.input.ContestRankInputDto;
import com.pokemonurpg.contest.service.ContestRankService;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/contestRank")
@CrossOrigin
@Validated
public class ContestRankController {

    @Resource
    private ContestRankService contestRankService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return contestRankService.findAllNames();
    }

    @AllowAll
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
