package com.pokemonurpg.security.controller;

import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.security.service.LoginService;
import com.pokemonurpg.security.service.RefreshService;
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
