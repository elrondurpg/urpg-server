package com.pokemonurpg.configuration.v1.creative.parkrank.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.creative.parkrank.ParkRankViews;
import com.pokemonurpg.configuration.v1.creative.parkrank.input.ParkRankInputDto;
import com.pokemonurpg.entities.v1.creative.ParkRank;
import com.pokemonurpg.configuration.v1.creative.parkrank.service.ParkRankService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/creative/parkrank")
@CrossOrigin
@Validated
public class ParkRankController extends NamedConfigurationController<ParkRank, FilterlessGetParams<ParkRank>, ParkRankInputDto> {

    @Autowired
    public ParkRankController(ParkRankService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ParkRankViews.Id.class)
            .withBriefViewClass(ParkRankViews.Brief.class)
            .withFullViewClass(ParkRankViews.Brief.class)
            .build(), service);
    }
}
