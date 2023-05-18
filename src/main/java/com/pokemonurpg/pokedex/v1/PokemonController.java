package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/urpg-pokedex/v1/pokemon")
@CrossOrigin
public class PokemonController {

    @Resource
    private PokedexService pokedexService;

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    PokemonResponse findByName(@PathVariable("name") String name) {
        return pokedexService.findByName(name);
    }
}
