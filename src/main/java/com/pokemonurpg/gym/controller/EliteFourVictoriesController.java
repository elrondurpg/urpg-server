package com.pokemonurpg.gym.controller;

import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.service.EliteFourVictoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/eliteFourVictories")
@CrossOrigin
public class EliteFourVictoriesController {

    @Resource
    private EliteFourVictoryService eliteFourVictoryService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    List<EliteFourVictory> findAll() {
        return eliteFourVictoryService.findAll();
    }
}
