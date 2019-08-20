package com.pokemonurpg.controller;

import com.pokemonurpg.dto.species.input.SpeciesInputDto;
import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.security.Authenticated;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class SpeciesController {

    private MemberService memberService;
    private SpeciesService speciesService;

    @Autowired
    public SpeciesController(MemberService memberService, SpeciesService speciesService) {
        this.memberService = memberService;
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
    ResponseEntity createSpecies(@RequestBody Authenticated<SpeciesInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                SpeciesInputDto species = input.getPayload();
                Errors errors = speciesService.createSpecies(species);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Pokemon " + species.getName() + " was created successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateSpecies(@RequestBody Authenticated<SpeciesInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                SpeciesInputDto species = input.getPayload();
                Errors errors = speciesService.updateSpecies(species);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Pokemon " + species.getName() + " was updated successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }

    /*
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
