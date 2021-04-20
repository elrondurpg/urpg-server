package com.pokemonurpg.contest.controller;

import com.pokemonurpg.contest.input.ContestMoveTypeInputDto;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.service.DPPContestMoveTypeService;
import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.core.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dppContestMoveType")
@CrossOrigin
@Validated
public class DPPContestMoveTypeController {

    @Resource
    private DPPContestMoveTypeService dppContestMoveTypeService;

    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return dppContestMoveTypeService.findAllNames();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    DPPContestMoveType findByName(@PathVariable("name") String name) {
        return dppContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @Authorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    DPPContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return dppContestMoveTypeService.create(input);
    }

    @Authorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    DPPContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return dppContestMoveTypeService.update(input, dbid);
    }
}
