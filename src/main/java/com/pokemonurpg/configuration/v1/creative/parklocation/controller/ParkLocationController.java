package com.pokemonurpg.configuration.v1.creative.parklocation.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.creative.parklocation.ParkLocationViews;
import com.pokemonurpg.configuration.v1.creative.parklocation.input.ParkLocationInputDto;
import com.pokemonurpg.entities.v1.creative.ParkLocation;
import com.pokemonurpg.configuration.v1.creative.parklocation.service.ParkLocationService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/creative/parklocation")
@CrossOrigin
@Validated
public class ParkLocationController extends NamedConfigurationController<ParkLocation, FilterlessGetParams<ParkLocation>, ParkLocationInputDto> {

    @Autowired
    public ParkLocationController(ParkLocationService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ParkLocationViews.Id.class)
            .withBriefViewClass(ParkLocationViews.Brief.class)
            .withFullViewClass(ParkLocationViews.Brief.class)
            .build(), service);
    }
}
