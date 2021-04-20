package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.AdvContestMoveType;
import com.pokemonurpg.contest.service.AdvContestMoveTypeService;
import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advContestMoveType")
@CrossOrigin
@Validated
public class AdvContestMoveTypeController {

    @Resource
    private AdvContestMoveTypeService advContestMoveTypeService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return advContestMoveTypeService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    AdvContestMoveType findByName(@PathVariable("name") String name) {
        return advContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    AdvContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return advContestMoveTypeService.create(input);
    }

    @Authorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    AdvContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return advContestMoveTypeService.update(input, dbid);
    }
}
