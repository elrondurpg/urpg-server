package com.pokemonurpg.creative.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.creative.input.ArtRankInputDto;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/artRank")
@CrossOrigin
@Validated
public class ArtRankController {

    @Resource
    private ArtRankService artRankService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return artRankService.findAllNames();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    ArtRank findByName(@PathVariable("name") String name) {
        return artRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Art")
    @PostMapping
    public @ResponseBody
    ArtRank create(@Valid @RequestBody ArtRankInputDto input) {
        return artRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Art")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ArtRank update(@Valid @RequestBody ArtRankInputDto input, @PathVariable int dbid) {
        return artRankService.update(input, dbid);
    }
}
