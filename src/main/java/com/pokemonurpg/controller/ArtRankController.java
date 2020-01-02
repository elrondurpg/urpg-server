package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.service.pokemon.ArtRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artrank")
@CrossOrigin
public class ArtRankController {

    private ArtRankService artRankService;

    @Autowired
    public ArtRankController(ArtRankService artRankService) {
        this.artRankService = artRankService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllArtRanks() {
        return new RestResponse(200, artRankService.findAll());
    }
}
