package com.pokemonurpg.member.controller;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.service.AuthenticationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/session")
@CrossOrigin
@Validated
public class SessionController {

    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public @ResponseBody
    SessionDto login(@Valid @RequestBody LoginInputDto input) {
        return authenticationService.login(input);
    }

    @PostMapping("/basic")
    public @ResponseBody
    SessionDto basicLogin() {
        return authenticationService.basicLogin(/* insert request data? */);
    }

    @PostMapping("/refresh")
    public @ResponseBody
    SessionDto refresh(@Valid @RequestBody SessionDto input) {
        return authenticationService.refresh(input);
    }
}
