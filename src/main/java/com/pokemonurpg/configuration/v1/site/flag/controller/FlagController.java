package com.pokemonurpg.configuration.v1.site.flag.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.site.flag.FlagViews;
import com.pokemonurpg.configuration.v1.site.flag.input.FlagInputDto;
import com.pokemonurpg.configuration.v1.site.flag.model.Flag;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/site/flag")
@CrossOrigin
@Validated
public class FlagController extends NamedConfigurationController<Flag, FilterlessGetParams<Flag>, FlagInputDto> {

    @Autowired
    public FlagController(FlagService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(FlagViews.Id.class)
            .withBriefViewClass(FlagViews.Brief.class)
            .withFullViewClass(FlagViews.Brief.class)
            .build(), service);
    }
}
