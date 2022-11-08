package com.pokemonurpg.configuration.v1.pokemon.ability.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/pokemon/ability")
@CrossOrigin
@Validated
public class AbilityController extends NamedConfigurationController<Ability, FilterlessGetParams<Ability>, AbilityInputDto> {

    @Autowired
    public AbilityController(AbilityService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(AbilityViews.Id.class)
            .withBriefViewClass(AbilityViews.Brief.class)
            .withFullViewClass(AbilityViews.Full.class)
            .build(), service);
    }
}
