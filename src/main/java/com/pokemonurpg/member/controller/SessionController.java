package com.pokemonurpg.member.controller;

import com.pokemonurpg.core.security.annotation.Authorized;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.service.AuthenticationService;
import com.pokemonurpg.member.service.LoginService;
import com.pokemonurpg.member.service.RefreshService;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
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
    private LoginService loginService;

    @Resource
    private RefreshService refreshService;

    @PostMapping("/login")
    public @ResponseBody
    SessionDto login(@Valid @RequestBody LoginInputDto input) {
        return loginService.login(input);
    }

    @PostMapping("/refresh")
    public @ResponseBody
    SessionDto refresh() {
        return refreshService.refresh();
    }
}
