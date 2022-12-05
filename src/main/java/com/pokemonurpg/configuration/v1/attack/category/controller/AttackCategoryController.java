package com.pokemonurpg.configuration.v1.attack.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.attack.category.AttackCategoryViews;
import com.pokemonurpg.configuration.v1.attack.category.input.AttackCategoryInputDto;
import com.pokemonurpg.entities.v1.attack.AttackCategory;
import com.pokemonurpg.configuration.v1.attack.category.service.AttackCategoryService;
import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

@RestController
@RequestMapping("/configuration/v1/attack/category")
@CrossOrigin
@Validated
public class AttackCategoryController extends NamedConfigurationController<AttackCategory, FilterlessGetParams<AttackCategory>, AttackCategoryInputDto> {

    @Autowired
    public AttackCategoryController(AttackCategoryService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(AttackCategoryViews.Id.class)
            .withBriefViewClass(AttackCategoryViews.Id.class)
            .withFullViewClass(AttackCategoryViews.Id.class)
            .build(), service);
    }
}
