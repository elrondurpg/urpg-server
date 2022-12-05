package com.pokemonurpg.configuration.v1.contest.type.controller;

import com.pokemonurpg.configuration.v1.contest.type.ContestTypeViews;
import com.pokemonurpg.configuration.v1.contest.type.input.ContestTypeInputDto;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.configuration.v1.contest.type.service.ContestTypeService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/contest/contestType")
@CrossOrigin
@Validated
public class ContestTypeController extends NamedConfigurationController<ContestType, FilterlessGetParams<ContestType>, ContestTypeInputDto>{
    @Autowired
    public ContestTypeController(ContestTypeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ContestTypeViews.Id.class)
            .withBriefViewClass(ContestTypeViews.Id.class)
            .withFullViewClass(ContestTypeViews.Id.class)
            .build(), service);
    }
}
