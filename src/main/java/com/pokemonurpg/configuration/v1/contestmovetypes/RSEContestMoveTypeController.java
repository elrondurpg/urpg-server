package com.pokemonurpg.configuration.v1.contestmovetypes;

import com.pokemonurpg.entities.RSEContestMoveType;
import com.pokemonurpg.login.annotation.AllowAll;
import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rseContestMoveType")
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