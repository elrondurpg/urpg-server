package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.lib.v1.annotations.AllowAuthorized;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.entities.v1.Pokemon;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/urpg-configuration/v1/pokemon")
@CrossOrigin
@Validated
public class PokemonController {

    @Resource
    private PokemonService pokemonService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames(@RequestParam(required = false) Boolean ownable) {
        return pokemonService.findAllNames(ownable);
    }

    @AllowAll
    @GetMapping(path="/starters")
    public @ResponseBody
    List<String> findAllStarterNames() { return pokemonService.findAllStarterNames(); }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    Pokemon findByName(@PathVariable("name") String name) {
        return pokemonService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Species")
    @PostMapping
    public @ResponseBody
    Pokemon create(@Valid @RequestBody PokemonRequest input) {
        return pokemonService.create(input);
    }

    @AllowAuthorized(permission = "Write Species")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    Pokemon update(@Valid @RequestBody PokemonRequest input, @PathVariable int dbid) {
        return pokemonService.update(input, dbid);
    }
}
