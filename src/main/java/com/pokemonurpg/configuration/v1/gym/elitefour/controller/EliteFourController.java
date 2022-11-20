package com.pokemonurpg.configuration.v1.gym.elitefour.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.elitefour.EliteFourViews;
import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.gym.elitefour.service.EliteFourService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/elitefour")
@CrossOrigin
@Validated
public class EliteFourController extends NamedConfigurationController<EliteFour, FilterlessGetParams<EliteFour>, EliteFourInputDto> {

    @Autowired
    public EliteFourController(EliteFourService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(EliteFourViews.Id.class)
            .withBriefViewClass(EliteFourViews.Brief.class)
            .withFullViewClass(EliteFourViews.Full.class)
            .build(), service);
    }
}
