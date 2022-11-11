package com.pokemonurpg.configuration.v1.pokemon.capturemethod.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.CaptureMethodViews;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.input.CaptureMethodInputDto;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.model.CaptureMethod;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/pokemon/capture-method")
@CrossOrigin
@Validated
public class CaptureMethodController extends NamedConfigurationController<CaptureMethod, FilterlessGetParams<CaptureMethod>, CaptureMethodInputDto>{

    @Autowired
    public CaptureMethodController(CaptureMethodService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(CaptureMethodViews.Id.class)
            .withBriefViewClass(CaptureMethodViews.Id.class)
            .withFullViewClass(CaptureMethodViews.Id.class)
            .build(), service);
    }
}
