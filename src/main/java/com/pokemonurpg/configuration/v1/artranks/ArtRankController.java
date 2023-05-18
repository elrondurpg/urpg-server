package com.pokemonurpg.configuration.v1.artranks;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.entities.v1.ArtRank;
import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/art-ranks")
@CrossOrigin
@Validated
public class ArtRankController {

    @Resource
    private ArtRankService artRankService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return artRankService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ArtRank findByName(@PathVariable("name") String name) {
        return artRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Art")
    @PostMapping
    public @ResponseBody
    ArtRank create(@Valid @RequestBody ArtRankRequest input) {
        return artRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Art")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ArtRank update(@Valid @RequestBody ArtRankRequest input, @PathVariable int dbid) {
        return artRankService.update(input, dbid);
    }
}
