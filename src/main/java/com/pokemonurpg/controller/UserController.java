package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.LoginDto;
import com.pokemonurpg.dto.security.RegisterBetaDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

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
    RestResponse login(@RequestBody LoginDto login) {
        String authToken = memberService.login(login);
        if (authToken == null) {
            return new RestResponse(401, "No user was found which matched the provided credentials.");
        }
        else {
            return new RestResponse(200, authToken);
        }
    }

    @PostMapping("/invite")
    public @ResponseBody
    RestResponse invite(@RequestBody Authenticated<String> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Invite User")) {
                String betaKey = memberService.inviteUser(input.getPayload());
                if (betaKey != null) {
                    return new RestResponse(200, betaKey);
                }
                else {
                    return new RestResponse(500, "An unexpected error occurred.");
                }
            }
            else return new RestResponse(401, "User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401, "User " + input.getUsername() + " could not be authenticated.");
    }

    @PutMapping("/registerBeta")
    public @ResponseBody
    RestResponse registerBeta(@RequestBody RegisterBetaDto input) {
        boolean success = memberService.registerBeta(input);
        if (success) {
            return new RestResponse(200, null);
        }
        else {
            return new RestResponse(401, "User " + input.getUsername() + " could not be registered.");
        }
    }

}
