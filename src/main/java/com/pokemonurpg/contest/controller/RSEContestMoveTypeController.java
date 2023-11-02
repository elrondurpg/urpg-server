package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.service.RSEContestMoveTypeService;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/rseContestMoveType")
@CrossOrigin
@Validated
public class RSEContestMoveTypeController {

    @Resource
    private RSEContestMoveTypeService rseContestMoveTypeService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return rseContestMoveTypeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    RSEContestMoveType findByName(@PathVariable("name") String name) {
        return rseContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    RSEContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return rseContestMoveTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    RSEContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return rseContestMoveTypeService.update(input, dbid);
    }
}
