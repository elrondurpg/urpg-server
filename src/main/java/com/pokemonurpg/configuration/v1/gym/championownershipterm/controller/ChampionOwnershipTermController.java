package com.pokemonurpg.configuration.v1.gym.championownershipterm.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.IndexedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.ChampionOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.input.ChampionOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.service.ChampionOwnershipTermService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/championownershipterm")
@CrossOrigin
@Validated
public class ChampionOwnershipTermController extends IndexedConfigurationController<ChampionOwnershipTerm, FilterlessGetParams<ChampionOwnershipTerm>, ChampionOwnershipTermInputDto> {

    @Autowired
    public ChampionOwnershipTermController(ChampionOwnershipTermService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ChampionOwnershipTermViews.Id.class)
            .withBriefViewClass(ChampionOwnershipTermViews.Brief.class)
            .withFullViewClass(ChampionOwnershipTermViews.Brief.class)
            .build(), service);
    }
}
