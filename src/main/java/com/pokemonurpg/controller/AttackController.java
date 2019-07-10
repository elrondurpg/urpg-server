package com.pokemonurpg.controller;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.service.AttackService;
import com.pokemonurpg.validator.AttackValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attack")
@CrossOrigin
public class AttackController {
    /*private AttackService service;

    private AttackValidator attackValidator;

    @Autowired
    public AttackController(AttackService service, AttackValidator attackValidator) {
        this.service = service;
        this.attackValidator = attackValidator;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity getAllAttack() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity getAttackByName(@PathVariable("name") String name) {
        Optional<Attack> attackOptional = service.findByName(name);
        if (attackOptional.isPresent()) {
            return ResponseEntity.ok(attackOptional.get());
        }
        else {
            List<Attack> results = service.findByNameStartingWith(name);
            if (results != null && !results.isEmpty()) {
                return ResponseEntity.ok(results.get(0));
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping(path="/create")
    public ResponseEntity createAttack(@RequestBody Attack attack) {
        Errors errors = attackValidator.validate(attack);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else {
            String name = attack.getName();
            Optional<Attack> attackOptional = service.findByName(name);
            if (attackOptional.isPresent()) {
                return ResponseEntity.badRequest().body("An Attack named " + name + " already exists!");
            }

            service.save(attack);
            return ResponseEntity.ok("Attack " + name + " was created successfully!");
        }
    }

    @PutMapping(path="/{name}")
    public ResponseEntity updateAttack(@RequestBody Attack attack, @PathVariable String name) {

        Optional<Attack> attackOptional = service.findByName(name);

        if (!attackOptional.isPresent())
            return ResponseEntity.notFound().build();
        else if (!name.equals(attack.getName()))
            return ResponseEntity.badRequest().build();
        else {
            attack.cloneValuesFrom(attackOptional.get());

            Errors errors = attackValidator.validate(attack);
            if(errors.hasErrors()) {
                return ResponseEntity.badRequest().body(errors.getAllErrors());
            }
            else {
                service.save(attack);
                return ResponseEntity.ok("Attack " + name + " was updated successfully!");
            }
        }
    }

    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteAttack(@PathVariable String name) {
        Optional<Attack> attackOptional = service.findByName(name);

        if (!attackOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            service.delete(attackOptional.get());
            return ResponseEntity.ok("Attack " + name + " was deleted successfully!");
        }
    }*/
}
