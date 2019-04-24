package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class SpeciesController {

    @Autowired
    private SpeciesRepository speciesRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    Species getSpeciesByName(@PathVariable("name") String name) {
        Optional<Species> speciesOptional = speciesRepository.findByName(name);
        return speciesOptional.orElseGet(() -> speciesRepository.findByNameStartingWith(name).get(0));
    }

    @PutMapping(path="/{name}")
    public ResponseEntity<Object> updateStudent(@RequestBody Species species, @PathVariable String name) {

        Optional<Species> speciesOptional = speciesRepository.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else {
            Integer dbid = speciesOptional.get().getDbid();
            species.setDbid(dbid);
            speciesRepository.save(species);
            return ResponseEntity.noContent().build();
        }


    }

}
