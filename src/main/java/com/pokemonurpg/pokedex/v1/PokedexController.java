package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
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
