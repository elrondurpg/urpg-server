package com.pokemonurpg.pokedex.controller;

import com.pokemonurpg.pokedex.output.PokedexEntryDto;
import com.pokemonurpg.pokedex.service.PokedexService;
import com.pokemonurpg.login.annotation.AllowAll;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class PokedexController {

    @Resource
    private PokedexService pokedexService;

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    PokedexEntryDto findByName(@PathVariable("name") String name) {
        return pokedexService.findByName(name);
    }
}
