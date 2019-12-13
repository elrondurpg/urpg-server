package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.stats.OwnedPokemonDto;
import com.pokemonurpg.dto.stats.StatsDto;
import com.pokemonurpg.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@CrossOrigin
public class StatsController {
    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getStatsByName(@PathVariable("name") String name) {
        try {
            StatsDto dto = statsService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }

    @GetMapping(path="/pokemon/{dbid}")
    public @ResponseBody
    RestResponse getOwnedPokemonByDbid(@PathVariable("dbid") int dbid) {
        try {
            OwnedPokemonDto dto = statsService.findOwnedPokemonByDbid(dbid);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }
}
