package com.pokemonurpg.configuration.v1.pokemon.nature.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.pokemon.nature.NatureViews;
import com.pokemonurpg.configuration.v1.pokemon.nature.input.NatureInputDto;
import com.pokemonurpg.configuration.v1.pokemon.nature.model.Nature;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/pokemon/nature")
@CrossOrigin
@Validated
public class NatureController extends NamedConfigurationController<Nature, FilterlessGetParams<Nature>, NatureInputDto> {

    @Autowired
    public NatureController(NatureService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(NatureViews.Id.class)
            .withBriefViewClass(NatureViews.Id.class)
            .withFullViewClass(NatureViews.Id.class)
            .build(), service);
    }
}
