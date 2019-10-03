package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.species.input.SpeciesInputDto;
import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.dto.security.Authenticated;
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

    @GetMapping
    public @ResponseBody
    RestResponse getAllSpecies() {
        return new RestResponse(200, speciesService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getSpeciesByName(@PathVariable("name") String name) {
        try {
            SpeciesDto dto = speciesService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }

    @PostMapping
    public @ResponseBody
    RestResponse createSpecies(@RequestBody Authenticated<SpeciesInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                SpeciesInputDto species = input.getPayload();
                Errors errors = speciesService.createSpecies(species);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200,"Pokemon " + species.getName() + " was created successfully!");
            }
            else return new RestResponse(401,"User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateSpecies(@RequestBody Authenticated<SpeciesInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                SpeciesInputDto species = input.getPayload();
                Errors errors = speciesService.updateSpecies(species);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200,"Pokemon " + species.getName() + " was updated successfully!");
            }
            else return new RestResponse(401,"User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }

    /*
    @DeleteMapping(path="/{name}")
    public ResponseEntity deleteSpecies(@PathVariable String name) {
        Optional<Species> speciesOptional = speciesService.findByUsername(name);

        if (!speciesOptional.isPresent())
            return ResponseEntity.notFound().build();
        else
        {
            speciesService.delete(speciesOptional.get());
            return ResponseEntity.ok("Pokemon " + name + " was deleted successfully!");
        }
    }*/

}
