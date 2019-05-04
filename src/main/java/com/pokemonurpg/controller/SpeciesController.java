package com.pokemonurpg.controller;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.service.SpeciesService;
import com.pokemonurpg.validator.SpeciesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class SpeciesController {

    private SpeciesService service;
    private SpeciesValidator speciesValidator = new SpeciesValidator();

    @Autowired
    public SpeciesController(SpeciesService service) {
        this.service = service;
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity getAllSpecies() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity getSpeciesByName(@PathVariable("name") String name) {
        Optional<Species> speciesOptional = service.findByName(name);
        if (speciesOptional.isPresent()) {
            return ResponseEntity.ok(speciesOptional.get());
        }
        else {
            List<Species> results = service.findByNameStartingWith(name);
            if (results != null && !results.isEmpty()) {
                return ResponseEntity.ok(results.get(0));
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity createSpecies(@RequestBody Species species) {
        Errors errors = speciesValidator.validate(species);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else {
            String name = species.getName();
            Optional<Species> speciesOptional = service.findByName(name);
            if (speciesOptional.isPresent()) {
                return ResponseEntity.badRequest().body("A Pokemon named " + name + " already exists!");
            }

            service.save(species);
            return ResponseEntity.ok("Pokemon " + name + " was created successfully!");
        }
    }

    @PutMapping(path="/{name}")
    public ResponseEntity updateSpecies(@RequestBody Species species, @PathVariable String name) {

        Optional<Species> speciesOptional = service.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else if (!name.equals(species.getName()))
            return ResponseEntity.badRequest().build();
        else {
            species.persistValuesFrom(speciesOptional.get());

            Errors errors = speciesValidator.validate(species);
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(errors.getAllErrors());
            }
            else {
                service.save(species);
                return ResponseEntity.ok("Pokemon " + name + " was updated successfully!");
            }
        }
    }

    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteSpecies(@PathVariable String name) {
        Optional<Species> speciesOptional = service.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            service.delete(speciesOptional.get());
            return ResponseEntity.ok("Pokemon " + name + " was deleted successfully!");
        }
    }

}
