package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class SpeciesController {

    private SpeciesService service;

    @Autowired
    public SpeciesController(SpeciesService service) {
        this.service = service;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Species> getAllSpecies() {
        return service.findAll();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Species getSpeciesByName(@PathVariable("name") String name) {
        Optional<Species> speciesOptional = service.findByName(name);
        return speciesOptional.orElseGet(() -> service.findByNameStartingWith(name).get(0));
    }

    @PutMapping(path="/{name}")
    public ResponseEntity<Species> updateSpecies(@RequestBody Species species, @PathVariable String name) {

        Optional<Species> speciesOptional = service.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else if (!name.equals(species.getName()))
            return ResponseEntity.badRequest().build();
        else {
            Integer dbid = speciesOptional.get().getDbid();
            species.setDbid(dbid);
            service.save(species);
            return ResponseEntity.noContent().build();
        }
    }

}
