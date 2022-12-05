package com.pokemonurpg.configuration.v1.contest.attribute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.contest.attribute.ContestAttributeViews;
import com.pokemonurpg.configuration.v1.contest.attribute.input.ContestAttributeInputDto;
import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.attribute.service.ContestAttributeService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

@RestController
@RequestMapping("/configuration/v1/contest/contestAttribute")
@CrossOrigin
@Validated
public class ContestAttributeController extends NamedConfigurationController<ContestAttribute, FilterlessGetParams<ContestAttribute>, ContestAttributeInputDto> {

    @Autowired
    public ContestAttributeController(ContestAttributeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(ContestAttributeViews.Id.class)
            .withBriefViewClass(ContestAttributeViews.Id.class)
            .withFullViewClass(ContestAttributeViews.Id.class)
            .build(), service);
    }
}
