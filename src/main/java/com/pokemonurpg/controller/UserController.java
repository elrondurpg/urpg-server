package com.pokemonurpg.controller;

import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.LoginDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private MemberService memberService;

    @Autowired
    public UserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity login(@RequestBody LoginDto login) {
        String authToken = memberService.login(login);
        if (authToken == null) {
            return ResponseEntity.status(401).body("No user was found which matched the provided credentials.");
        }
        else {
            return ResponseEntity.ok(authToken);
        }
    }

    @PostMapping("/invite")
    public @ResponseBody
    ResponseEntity invite(@RequestBody Authenticated<String> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Invite User")) {
                String betaKey = memberService.inviteUser(input.getPayload());
                if (betaKey != null) {
                    return ResponseEntity.ok(betaKey);
                }
                else {
                    return ResponseEntity.status(500).body("An unexpected error occurred.");
                }
            }
            else return ResponseEntity.status(401).body("User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return ResponseEntity.status(401).body("User " + input.getUsername() + " could not be authenticated.");
    }

}
