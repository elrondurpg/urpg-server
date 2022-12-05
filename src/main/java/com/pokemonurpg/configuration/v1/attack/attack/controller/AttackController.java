package com.pokemonurpg.configuration.v1.attack.attack.controller;

import com.pokemonurpg.lib.input.v1.FilterlessGetParams;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.attack.attack.input.AttackInputDto;
import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.configuration.v1.attack.attack.service.AttackService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("configuration/v1/attack/attack")
@CrossOrigin
@Validated
public class AttackController extends NamedConfigurationController<Attack, FilterlessGetParams<Attack>, AttackInputDto> {

    @Autowired
    public AttackController(AttackService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(AttackViews.Id.class)
            .withBriefViewClass(AttackViews.Brief.class)
            .withFullViewClass(AttackViews.Full.class)
            .build(), service);
    }
}
