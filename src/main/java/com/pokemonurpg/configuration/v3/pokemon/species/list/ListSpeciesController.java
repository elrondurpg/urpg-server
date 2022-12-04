package com.pokemonurpg.configuration.v3.pokemon.species.list;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.controller.ListController;
import com.pokemonurpg.configuration.v3.shared.interactor.ListInputBoundary;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/configuration/v3/pokemon/species")
@CrossOrigin
@Validated
@Api(tags = "Species", description = "APIs related to Species configuration")
public class ListSpeciesController extends ListController<ListSpeciesView, ListSpeciesRequest> {
    public ListSpeciesController(ListInputBoundary<ListSpeciesView, ListSpeciesRequest> inputBoundary) {
        super(inputBoundary);
    }
}
