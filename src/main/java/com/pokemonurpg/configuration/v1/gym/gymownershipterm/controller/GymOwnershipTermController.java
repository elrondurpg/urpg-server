package com.pokemonurpg.configuration.v1.gym.gymownershipterm.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.IndexedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.GymOwnershipTermViews;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.input.GymOwnershipTermInputDto;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/gymownershipterm")
@CrossOrigin
@Validated
public class GymOwnershipTermController extends IndexedConfigurationController<GymOwnershipTerm, FilterlessGetParams<GymOwnershipTerm>, GymOwnershipTermInputDto> {

    @Autowired
    public GymOwnershipTermController(GymOwnershipTermService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(GymOwnershipTermViews.Id.class)
            .withBriefViewClass(GymOwnershipTermViews.Brief.class)
            .withFullViewClass(GymOwnershipTermViews.Brief.class)
            .build(), service);
    }
}
