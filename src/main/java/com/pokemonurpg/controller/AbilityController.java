package com.pokemonurpg.controller;

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

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity<Ability> getAbilityByName(@PathVariable("name") String name) {
        try {
            Ability dto = abilityService.findByName(name);
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
    ResponseEntity createAbility(@RequestBody Authenticated<Ability> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Ability")) {
                Ability ability = input.getPayload();
                Errors errors = abilityService.createAbility(ability);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Ability " + ability.getName() + " was created successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateAbility(@RequestBody Authenticated<Ability> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                Ability ability = input.getPayload();
                Errors errors = abilityService.updateAbility(ability);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Ability " + ability.getName() + " was updated successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }
}
