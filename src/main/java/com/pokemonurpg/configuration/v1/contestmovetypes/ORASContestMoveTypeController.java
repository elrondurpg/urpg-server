package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.entities.ORASContestMoveType;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return orasContestMoveTypeService.findAllNames();
    }

    @AllowAll
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
