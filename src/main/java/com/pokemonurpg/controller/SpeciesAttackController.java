package com.pokemonurpg.controller;

import com.pokemonurpg.dto.species.input.SpeciesInputDto;
import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.service.SpeciesAttackService;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/speciesAttack")
@CrossOrigin
public class SpeciesAttackController {

    private SpeciesAttackService speciesAttackService;

    @Autowired
    public SpeciesAttackController(SpeciesAttackService speciesAttackService) {
        this.speciesAttackService = speciesAttackService;
    }

    /*@GetMapping(path="/all")
    public @ResponseBody ResponseEntity getAllSpecies() {
        return ResponseEntity.ok(speciesService.findAll());
    }*/

    /*// TODO Null ID
    @PutMapping(path="/text/{species}/{attack}")
    public @ResponseBody
    void createSpecies(@PathVariable String species, @PathVariable String attack) {
        speciesAttackService.create(species, attack);
    }

    // TODO WORKS
    @PutMapping(path="/dbid/{species}/{attack}")
    public @ResponseBody
    void createSpecies(@PathVariable int species, @PathVariable int attack) {
        speciesAttackService.create(species, attack);
    }

    // Not-null property references a transient value
    @PutMapping(path="/mixtextfirst/{species}/{attack}")
    public @ResponseBody
    void createSpecies(@PathVariable String species, @PathVariable int attack) {
        speciesAttackService.create(species, attack);
    }

    // Not-null property references a transient value
    @PutMapping(path="/mixdbidfirst/{species}/{attack}")
    public @ResponseBody
    void createSpecies(@PathVariable int species, @PathVariable String attack) {
        speciesAttackService.create(species, attack);
    }*/

}
