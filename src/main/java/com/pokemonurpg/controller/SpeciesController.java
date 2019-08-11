package com.pokemonurpg.controller;

import com.pokemonurpg.dto.species.input.SpeciesInputDto;
import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class SpeciesController {

    private SpeciesService speciesService;

    @Autowired
    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    /*@GetMapping(path="/all")
    public @ResponseBody ResponseEntity getAllSpecies() {
        return ResponseEntity.ok(speciesService.findAll());
    }*/

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity<SpeciesDto> getSpeciesByName(@PathVariable("name") String name) {
        try {
            SpeciesDto dto = speciesService.findByName(name);
            if (dto != null) {
                return ResponseEntity.ok(dto);
            }
            else return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity createSpecies(@RequestBody SpeciesInputDto species) {
        Errors errors = speciesService.create(species);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else return ResponseEntity.ok("Pokemon " + species.getName() + " was created successfully!");
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateSpecies(@RequestBody SpeciesInputDto species) {
        Errors errors = speciesService.update(species);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else return ResponseEntity.ok("Pokemon " + species.getName() + " was updated successfully!");
    }

    /*@PostMapping(path="/create")
    public ResponseEntity createSpecies(@RequestBody Species species) {
        Errors errors = speciesValidator.validate(species);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else {
            String name = species.getName();
            Optional<Species> speciesOptional = speciesService.findByName(name);
            if (speciesOptional.isPresent()) {
                return ResponseEntity.badRequest().body("A Pokemon named " + name + " already exists!");
            }

            List<SpeciesAttack> attacks = species.getAttacks();
            for (SpeciesAttack attack : attacks) {
                errors = speciesAttackValidator.validate(attack);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
            }

            List<SpeciesAbility> abilities = species.getAbilities();
            for (SpeciesAbility ability : abilities) {
                errors = speciesAbilityValidator.validate(ability);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
            }

            speciesService.save(species);
            return ResponseEntity.ok("Pokemon " + name + " was created successfully!");
        }
    }

    @PutMapping(path="/{name}")
    public ResponseEntity updateSpecies(@RequestBody Species species, @PathVariable String name) {

        Optional<Species> speciesOptional = speciesService.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else if (!name.equals(species.getName()))
            return ResponseEntity.badRequest().build();
        else {
            species.cloneValuesFrom(speciesOptional.get());

            Errors errors = speciesValidator.validate(species);
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(errors.getAllErrors());
            }
            else {
                speciesService.save(species);
                return ResponseEntity.ok("Pokemon " + name + " was updated successfully!");
            }
        }
    }

    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteSpecies(@PathVariable String name) {
        Optional<Species> speciesOptional = speciesService.findByName(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            speciesService.delete(speciesOptional.get());
            return ResponseEntity.ok("Pokemon " + name + " was deleted successfully!");
        }
    }*/

}
