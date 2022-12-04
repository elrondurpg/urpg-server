package com.pokemonurpg.configuration.v3.pokemon.species.getByName;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesView;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/configuration/v3/pokemon/species")
@CrossOrigin
@Validated
@Api(tags = "Species")
public class GetSpeciesByNameController {
    
    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody GetSpeciesView getByName(@PathVariable("name") String name) {
        return null;
    }
}
