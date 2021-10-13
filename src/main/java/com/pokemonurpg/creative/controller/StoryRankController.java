package com.pokemonurpg.creative.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.creative.input.StoryRankInputDto;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.security.annotation.AllowAuthorized;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/storyRank")
@CrossOrigin
@Validated
public class StoryRankController {

    @Resource
    private StoryRankService storyRankService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return storyRankService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    StoryRank findByName(@PathVariable("name") String name) {
        return storyRankService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Story")
    @PostMapping
    public @ResponseBody
    StoryRank create(@Valid @RequestBody StoryRankInputDto input) {
        return storyRankService.create(input);
    }

    @AllowAuthorized(permission = "Write Story")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    StoryRank update(@Valid @RequestBody StoryRankInputDto input, @PathVariable int dbid) {
        return storyRankService.update(input, dbid);
    }
}
