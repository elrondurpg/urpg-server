package com.pokemonurpg.configuration.v1.gym.gym.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.gym.GymViews;
import com.pokemonurpg.configuration.v1.gym.gym.input.GymInputDto;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/gym")
@CrossOrigin
@Validated
public class GymController extends NamedConfigurationController<Gym, FilterlessGetParams<Gym>, GymInputDto> {

    @Autowired
    public GymController(GymService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(GymViews.Id.class)
            .withBriefViewClass(GymViews.Brief.class)
            .withFullViewClass(GymViews.Full.class)
            .build(), service);
    }
}
