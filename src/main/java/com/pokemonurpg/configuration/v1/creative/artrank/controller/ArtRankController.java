package com.pokemonurpg.configuration.v1.creative.artrank.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.creative.artrank.ArtRankViews;
import com.pokemonurpg.configuration.v1.creative.artrank.input.ArtRankInputDto;
import com.pokemonurpg.entities.v1.creative.ArtRank;
import com.pokemonurpg.configuration.v1.creative.artrank.service.ArtRankService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/creative/artrank")
@CrossOrigin
@Validated
public class ArtRankController extends NamedConfigurationController<ArtRank, FilterlessGetParams<ArtRank>, ArtRankInputDto> {

    @Autowired
    public ArtRankController(ArtRankService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ArtRankViews.Id.class)
            .withBriefViewClass(ArtRankViews.Brief.class)
            .withFullViewClass(ArtRankViews.Brief.class)
            .build(), service);
    }
}
