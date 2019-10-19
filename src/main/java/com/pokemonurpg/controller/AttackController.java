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
    RestResponse getAttackByName(@PathVariable("name") String name) {
        try {
            AttackDto dto = attackService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, "Attack not found.");
        } catch (IllegalStateException e) {
            return new RestResponse(400, "Bad request.");
        }
    }

    @PostMapping
    public @ResponseBody
    RestResponse createAttack(@RequestBody Authenticated<AttackInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Attack")) {
            AttackInputDto attack = input.getPayload();
            Errors errors = attackService.createAttack(attack);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200, "Attack " + attack.getName() + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateAttack(@RequestBody Authenticated<AttackInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Attack")) {
            AttackInputDto attack = input.getPayload();
            Errors errors = attackService.updateAttack(attack);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200, "Attack " + attack.getName() + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }
}
