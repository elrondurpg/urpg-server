package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.entities.DPPContestMoveType;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
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

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return dppContestMoveTypeService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    DPPContestMoveType findByName(@PathVariable("name") String name) {
        return dppContestMoveTypeService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Contest Type")
    @PostMapping
    public @ResponseBody
    DPPContestMoveType create(@Valid @RequestBody ContestMoveTypeInputDto input) {
        return dppContestMoveTypeService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    DPPContestMoveType update(@Valid @RequestBody ContestMoveTypeInputDto input, @PathVariable int dbid) {
        return dppContestMoveTypeService.update(input, dbid);
    }
}
