package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.StoryRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storyrank")
@CrossOrigin
public class StoryRankController {

    private StoryRankService storyRankService;

    @Autowired
    public StoryRankController(StoryRankService storyRankService) {
        this.storyRankService = storyRankService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllSpecies() {
        return new RestResponse(200, storyRankService.findAll());
    }
}
