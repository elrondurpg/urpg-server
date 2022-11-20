package com.pokemonurpg.configuration.v1.gym.champion.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.champion.ChampionViews;
import com.pokemonurpg.configuration.v1.gym.champion.input.ChampionInputDto;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.champion.service.ChampionService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/champion")
@CrossOrigin
@Validated
public class ChampionController extends NamedConfigurationController<Champion, FilterlessGetParams<Champion>, ChampionInputDto> {

    @Autowired
    public ChampionController(ChampionService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ChampionViews.Id.class)
            .withBriefViewClass(ChampionViews.Brief.class)
            .withFullViewClass(ChampionViews.Full.class)
            .build(), service);
    }
}
