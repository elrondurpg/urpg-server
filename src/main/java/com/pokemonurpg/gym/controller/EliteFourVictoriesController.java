package com.pokemonurpg.gym.controller;

import com.pokemonurpg.security.annotation.AllowAll;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.service.EliteFourVictoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/urpg-server/eliteFourVictories")
@CrossOrigin
public class EliteFourVictoriesController {

    @Resource
    private EliteFourVictoryService eliteFourVictoryService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<EliteFourVictory> findAll() {
        return eliteFourVictoryService.findAll();
    }
}
