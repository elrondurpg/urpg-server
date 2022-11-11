package com.pokemonurpg.configuration.v1.pokemon.type.controller;

import com.pokemonurpg.configuration.v1.lib.controller.ConfigControllerDefinition;
import com.pokemonurpg.configuration.v1.lib.controller.NamedConfigurationController;
import com.pokemonurpg.configuration.v1.pokemon.type.TypeViews;
import com.pokemonurpg.configuration.v1.pokemon.type.input.TypeInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.lib.input.v1.FilterlessGetParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v1/pokemon/type")
@CrossOrigin
@Validated
public class TypeController extends NamedConfigurationController<Type, FilterlessGetParams<Type>, TypeInputDto> {
    @Autowired
    public TypeController(TypeService service) {
        super(new ConfigControllerDefinition.Builder()
            .withIdViewClass(TypeViews.Id.class)
            .withBriefViewClass(TypeViews.Id.class)
            .withFullViewClass(TypeViews.Id.class)
            .build(), service);
    }
}
