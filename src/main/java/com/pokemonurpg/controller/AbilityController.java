package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.object.Ability;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.service.AbilityService;
import com.pokemonurpg.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ability")
@CrossOrigin
public class AbilityController {
    private AbilityService abilityService;
    private MemberService memberService;

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
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Ability")) {
                Ability ability = input.getPayload();
                Errors errors = abilityService.createAbility(ability);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200,"Ability " + ability.getName() + " was created successfully!");
            }
            else return new RestResponse(401,"User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateAbility(@RequestBody Authenticated<Ability> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Ability")) {
                Ability ability = input.getPayload();
                Errors errors = abilityService.updateAbility(ability);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200,"Ability " + ability.getName() + " was updated successfully!");
            }
            else return new RestResponse(401,"User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }
}
