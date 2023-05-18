package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/contest-ranks")
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
    ContestRank create(@Valid @RequestBody ContestRankRequest input) {
        return contestRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Contest Type")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ContestRank update(@Valid @RequestBody ContestRankRequest input, @PathVariable int dbid) {
        return contestRankService.update(input, dbid);
    }
}
