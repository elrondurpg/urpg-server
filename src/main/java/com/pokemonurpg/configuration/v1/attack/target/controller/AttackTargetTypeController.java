package com.pokemonurpg.configuration.v1.attack.target.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.attack.target.AttackTargetTypeViews;
import com.pokemonurpg.configuration.v1.attack.target.input.AttackTargetTypeInputDto;
import com.pokemonurpg.configuration.v1.attack.target.model.AttackTargetType;
import com.pokemonurpg.configuration.v1.attack.target.service.AttackTargetTypeService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/attack/target")
@CrossOrigin
@Validated
public class AttackTargetTypeController extends NamedConfigurationController<AttackTargetType, FilterlessGetParams<AttackTargetType>, AttackTargetTypeInputDto> {

    @Autowired
    public AttackTargetTypeController(AttackTargetTypeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(AttackTargetTypeViews.Id.class)
            .withBriefViewClass(AttackTargetTypeViews.Brief.class)
            .withFullViewClass(AttackTargetTypeViews.Brief.class)
            .build(), service);
    }
}
