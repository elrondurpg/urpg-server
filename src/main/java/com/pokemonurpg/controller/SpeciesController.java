package com.pokemonurpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.SessionDto;
import com.pokemonurpg.dto.species.input.SpeciesInputDto;
import com.pokemonurpg.dto.species.response.SpeciesDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.SpeciesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private Logger logger = LogManager.getLogger(SpeciesController.class);
    private ObjectMapper mapper = new ObjectMapper();

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
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Species")) {
            SpeciesInputDto species = input.getPayload();
            try {
                logger.info("{} requested CREATE SPECIES with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = speciesService.createSpecies(species);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            } else return new RestResponse(200, "Pokemon " + species.getName() + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateSpecies(@RequestBody Authenticated<SpeciesInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Species")) {
            SpeciesInputDto species = input.getPayload();
            try {
                logger.info("{} requested UPDATE SPECIES with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = speciesService.updateSpecies(species);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            } else return new RestResponse(200, "Pokemon " + species.getName() + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
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
