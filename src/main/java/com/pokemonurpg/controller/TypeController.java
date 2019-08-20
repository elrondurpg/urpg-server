package com.pokemonurpg.controller;

import com.pokemonurpg.object.Type;
import com.pokemonurpg.service.TypeService;
import com.pokemonurpg.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/type")
@CrossOrigin
public class TypeController {
    /*private TypeService service;
    private TypeValidator typeValidator;

    @Autowired
    public TypeController(TypeService service, TypeValidator typeValidator) {
        this.service = service;
        this.typeValidator = typeValidator;
    }

    @GetMapping(path="/all")
    public @ResponseBody
    ResponseEntity getAllType() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity getTypeByName(@PathVariable("name") String name) {
        Optional<Type> typeOptional = service.findByName(name);
        if (typeOptional.isPresent()) {
            return ResponseEntity.ok(typeOptional.get());
        }
        else {
            List<Type> results = service.findByNameStartingWith(name);
            if (results != null && !results.isEmpty()) {
                return ResponseEntity.ok(results.get(0));
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping(path="/createSpecies")
    public ResponseEntity createType(@RequestBody Type type) {
        Errors errors = typeValidator.validate(type);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        else {
            String name = type.getName();
            Optional<Type> typeOptional = service.findByName(name);
            if (typeOptional.isPresent()) {
                return ResponseEntity.badRequest().body("A Type named " + name + " already exists!");
            }

            service.save(type);
            return ResponseEntity.ok("Type " + name + " was created successfully!");
        }
    }

    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteType(@PathVariable String name) {
        Optional<Type> typeOptional = service.findByName(name);

        if (!typeOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            service.delete(typeOptional.get());
            return ResponseEntity.ok("Type " + name + " was deleted successfully!");
        }
    }*/
}
