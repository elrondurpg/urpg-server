package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.*;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
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

    @GetMapping
    public @ResponseBody
    RestResponse getAllMembers() {
        return new RestResponse(200, memberService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getRoleByName(@PathVariable("name") String name) {
        try {
            MemberDto dto = memberService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, "Member not found.");
        } catch (IllegalStateException e) {
            return new RestResponse(400, "Bad request.");
        }
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
                Member existingMember = memberService.findByExactName(input.getPayload());
                if (existingMember != null) {
                    return new RestResponse(400, "A user with that name already exists!");
                }

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

    @PutMapping
    public @ResponseBody
    RestResponse updateMember(@RequestBody Authenticated<MemberInputDto> input) {
        Member member = memberService.authenticate(input);
        if (member != null) {
            if (memberService.authorize(member, "Write Member")) {
                MemberInputDto memberToUpdate = input.getPayload();
                Errors errors = memberService.updateMember(memberToUpdate);
                if (errors.hasErrors()) {
                    return new RestResponse(400, errors.getAllErrors());
                }
                else return new RestResponse(200, "User " + memberToUpdate.getName() + " was updated successfully!");
            }
            else return new RestResponse(401, "User " + input.getUsername() + " does not have permission to perform the requested action.");
        }
        else return new RestResponse(401,"User " + input.getUsername() + " could not be authenticated.");
    }

    /*@PutMapping("/password")
    public @ResponseBody
    RestResponse changePassword(@RequestBody ChangePasswordDto input) {
        boolean success = memberService.changePassword(input);
        if (success) {
            return new RestResponse(200, null);
        }
        else {
            return new RestResponse(401, "User " + input.getUsername() + "'s password could not be changed.");
        }
    }*/

}
