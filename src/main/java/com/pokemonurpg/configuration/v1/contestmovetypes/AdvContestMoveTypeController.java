package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.entities.AdvContestMoveType;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return advContestMoveTypeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    AdvContestMoveType findByName(@PathVariable("name") String name) {
        return advContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    AdvContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return advContestMoveTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    AdvContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return advContestMoveTypeService.update(input, dbid);
    }
}
