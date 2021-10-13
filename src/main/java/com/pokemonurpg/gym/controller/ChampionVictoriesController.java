package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.service.ChampionVictoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/championVictories")
@CrossOrigin
public class ChampionVictoriesController {

    @Resource
    private ChampionVictoryService championVictoryService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<ChampionVictory> findAll() {
        return championVictoryService.findAll();
    }
}
