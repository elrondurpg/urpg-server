package com.pokemonurpg.configuration.v1.gym.league.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.league.LeagueViews;
import com.pokemonurpg.configuration.v1.gym.league.input.LeagueInputDto;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.gym.league.service.LeagueService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/league")
@CrossOrigin
@Validated
public class LeagueController extends NamedConfigurationController<League, FilterlessGetParams<League>, LeagueInputDto> {

    @Autowired
    public LeagueController(LeagueService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(LeagueViews.Id.class)
            .withBriefViewClass(LeagueViews.Id.class)
            .withFullViewClass(LeagueViews.Id.class)
            .build(), service);
    }
}
