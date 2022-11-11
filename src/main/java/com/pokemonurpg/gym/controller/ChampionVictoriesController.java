package com.pokemonurpg.gym.controller;

import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
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

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<ChampionVictory> findAll() {
        return championVictoryService.findAll();
    }
}
