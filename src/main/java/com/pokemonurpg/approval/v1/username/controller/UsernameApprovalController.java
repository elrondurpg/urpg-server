package com.pokemonurpg.approval.v1.username.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_CLAIM;

@RestController
@RequestMapping("/approval/v1/username")
@CrossOrigin
public class UsernameApprovalController {
    @Resource
    private MemberService memberService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_AUTHORIZED)
    @PostMapping(path="/{discordId}")
    public @ResponseBody
    ResponseEntity approveKnownNameClaim(@PathVariable String discordId) {
        try {
            memberService.registerForKnownName(discordId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_CLAIM);
        }
    }
}
