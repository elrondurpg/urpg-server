package com.pokemonurpg.configuration.v1.gym.badge.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.gym.badge.BadgeViews;
import com.pokemonurpg.configuration.v1.gym.badge.input.BadgeInputDto;
import com.pokemonurpg.configuration.v1.gym.badge.model.Badge;
import com.pokemonurpg.configuration.v1.gym.badge.service.BadgeService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/gym/badge")
@CrossOrigin
@Validated
public class BadgeController extends NamedConfigurationController<Badge, FilterlessGetParams<Badge>, BadgeInputDto> {

    @Autowired
    public BadgeController(BadgeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(BadgeViews.Id.class)
            .withBriefViewClass(BadgeViews.Id.class)
            .withFullViewClass(BadgeViews.Id.class)
            .build(), service);
    }
}
