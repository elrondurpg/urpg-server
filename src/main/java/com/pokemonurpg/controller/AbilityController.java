package com.pokemonurpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.RestResponse;
import com.pokemonurpg.URPGServerApplication;
import com.pokemonurpg.object.Ability;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.service.AbilityService;
import com.pokemonurpg.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ability")
@CrossOrigin
public class AbilityController {
    private AbilityService abilityService;
    private MemberService memberService;
    private Logger logger = LogManager.getLogger(AbilityController.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AbilityController(AbilityService abilityService, MemberService memberService) {
        this.abilityService = abilityService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllAbilities() {
        return new RestResponse(200, abilityService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getAbilityByName(@PathVariable("name") String name) {
        try {
            Ability dto = abilityService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, "Ability not found.");
        } catch (IllegalStateException e) {
            return new RestResponse(400, "Bad request.");
        }
    }

    @PostMapping
    public @ResponseBody
    RestResponse createAbility(@RequestBody Authenticated<Ability> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Ability")) {
            Ability ability = input.getPayload();
            try {
                logger.info("{} requested CREATE ABILITY with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = abilityService.createAbility(ability);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200,"Ability " + ability.getName() + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateAbility(@RequestBody Authenticated<Ability> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Ability")) {
            Ability ability = input.getPayload();
            try {
                logger.info("{} requested UPDATE ABILITY with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = abilityService.updateAbility(ability);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            } else return new RestResponse(200, "Ability " + ability.getName() + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }
}
