package com.pokemonurpg.configuration.v1.pokemon.species.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v1.lib.config.PagedConfiguration;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesGetParams;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/configuration/v1/pokemon/species")
@CrossOrigin
@Validated
@AllArgsConstructor
public class ListSpeciesController {
    protected SpeciesService service;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping
    public @ResponseBody
    PagedConfiguration<ListSpeciesView> getList(@Valid SpeciesGetParams params) {
        Page<Species> page = service.getList(params);
        return new PagedConfiguration<ListSpeciesView>(page, ListSpeciesView.class);
    }
}
