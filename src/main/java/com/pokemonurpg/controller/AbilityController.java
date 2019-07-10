package com.pokemonurpg.controller;

import com.pokemonurpg.object.Ability;
import com.pokemonurpg.service.AbilityService;
import com.pokemonurpg.validator.AbilityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ability")
@CrossOrigin
public class AbilityController {
    /*private AbilityService service;

    private AbilityValidator abilityValidator;

    @Autowired
    public AbilityController(AbilityService service, AbilityValidator abilityValidator) {
        this.service = service;
        this.abilityValidator = abilityValidator;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity getAllAbility() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity getAbilityByName(@PathVariable("name") String name) {
        Optional<Ability> abilityOptional = service.findByName(name);
        if (abilityOptional.isPresent()) {
            return ResponseEntity.ok(abilityOptional.get());
        }
        else {
            List<Ability> results = service.findByNameStartingWith(name);
            if (results != null && !results.isEmpty()) {
                return ResponseEntity.ok(results.get(0));
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity createAbility(@RequestBody Ability ability) {
        Errors errors = abilityValidator.validate(ability);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else {
            String name = ability.getName();
            Optional<Ability> abilityOptional = service.findByName(name);
            if (abilityOptional.isPresent()) {
                return ResponseEntity.badRequest().body("An Ability named " + name + " already exists!");
            }

            service.save(ability);
            return ResponseEntity.ok("Ability " + name + " was created successfully!");
        }
    }

    @PutMapping(path="/{name}")
    public ResponseEntity updateAbility(@RequestBody Ability ability, @PathVariable String name) {

        Optional<Ability> abilityOptional = service.findByName(name);

        if (!abilityOptional.isPresent())
            return ResponseEntity.notFound().build();
        else if (!name.equals(ability.getName()))
            return ResponseEntity.badRequest().build();
        else {
            ability.cloneValuesFrom(abilityOptional.get());

            Errors errors = abilityValidator.validate(ability);
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(errors.getAllErrors());
            }
            else {
                service.save(ability);
                return ResponseEntity.ok("Ability " + name + " was updated successfully!");
            }
        }
    }

    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteAbility(@PathVariable String name) {
        Optional<Ability> abilityOptional = service.findByName(name);

        if (!abilityOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            service.delete(abilityOptional.get());
            return ResponseEntity.ok("Ability " + name + " was deleted successfully!");
        }
    }*/
}
