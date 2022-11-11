package com.pokemonurpg.pokedex.controller;

import com.pokemonurpg.pokedex.output.PokedexEntryDto;
import com.pokemonurpg.pokedex.service.PokedexService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class PokedexController {

    @Resource
    private PokedexService pokedexService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @GetMapping(path="/{name}")
    public @ResponseBody
    PokedexEntryDto findByName(@PathVariable("name") String name) {
        return pokedexService.findByName(name);
    }
}
