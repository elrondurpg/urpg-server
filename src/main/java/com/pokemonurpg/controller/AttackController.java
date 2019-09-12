package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.attack.AttackDto;
import com.pokemonurpg.dto.attack.AttackInputDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.service.AttackService;
import com.pokemonurpg.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attack")
@CrossOrigin
public class AttackController {

    private AttackService attackService;
    private MemberService memberService;

    @Autowired
    public AttackController(AttackService attackService, MemberService memberService) {
        this.attackService = attackService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllAttacks() {
        return new RestResponse(200, attackService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    ResponseEntity<AttackDto> getAttackByName(@PathVariable("name") String name) {
        try {
            AttackDto dto = attackService.findByName(name);
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
    ResponseEntity createAttack(@RequestBody Authenticated<AttackInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Attack")) {
                AttackInputDto attack = input.getPayload();
                Errors errors = attackService.createAttack(attack);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Attack " + attack.getName() + " was created successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateAttack(@RequestBody Authenticated<AttackInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Species")) {
                AttackInputDto attack = input.getPayload();
                Errors errors = attackService.updateAttack(attack);
                if (errors.hasErrors()) {
                    return ResponseEntity.badRequest().body(errors.getAllErrors());
                }
                else return ResponseEntity.ok("Attack " + attack.getName() + " was updated successfully!");
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }
}
