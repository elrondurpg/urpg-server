package com.pokemonurpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.*;
import com.pokemonurpg.service.trainer.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private MemberService memberService;
    private Logger logger = LogManager.getLogger(UserController.class);
    private ObjectMapper mapper = new ObjectMapper();

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
    RestResponse getMember(@PathVariable("name") String name) {
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

    @PostMapping("/session")
    public @ResponseBody
    RestResponse session(@RequestBody SessionDto input) {
        SessionDto currentSession = memberService.refreshCurrentUserSession(input);
        if (currentSession != null) {
            return new RestResponse(200, currentSession);
        }
        else return new RestResponse(404, "No current session found.");
    }

    @PostMapping("/login")
    public @ResponseBody
    RestResponse login(@RequestBody String code) {
        code = code.replaceAll("\"", "");
        SessionDto session = memberService.login(code);
        if (session != null) {
            return new RestResponse(200, session);
        }
        else return new RestResponse(401, "Couldn't log you in. Please contact your system administrator.");
    }

    @PostMapping("/invite")
    public @ResponseBody
    RestResponse invite(@RequestBody Authenticated<InviteUserDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Invite User")) {
            try {
                logger.info("{} requested INVITE USER with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = memberService.inviteUser(input.getPayload());
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200,"User " + input.getPayload().getUsername() + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateMember(@RequestBody Authenticated<MemberInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Member")) {
            MemberInputDto memberToUpdate = input.getPayload();
            try {
                logger.info("{} requested UPDATE MEMBER with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = memberService.updateMember(memberToUpdate);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            } else
                return new RestResponse(200, "User " + memberToUpdate.getName() + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
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
