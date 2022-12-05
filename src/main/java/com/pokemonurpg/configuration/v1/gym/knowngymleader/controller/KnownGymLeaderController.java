package com.pokemonurpg.configuration.v1.gym.knowngymleader.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.KnownGymLeaderViews;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.input.KnownGymLeaderInputDto;
import com.pokemonurpg.entities.v1.gym.KnownGymLeader;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/knowngymleader")
@CrossOrigin
@Validated
public class KnownGymLeaderController extends NamedConfigurationController<KnownGymLeader, FilterlessGetParams<KnownGymLeader>, KnownGymLeaderInputDto> {

    @Autowired
    public KnownGymLeaderController(KnownGymLeaderService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(KnownGymLeaderViews.Id.class)
            .withBriefViewClass(KnownGymLeaderViews.Id.class)
            .withFullViewClass(KnownGymLeaderViews.Id.class)
            .build(), service);
    }
}
