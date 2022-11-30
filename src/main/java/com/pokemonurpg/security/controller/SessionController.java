package com.pokemonurpg.security.controller;

import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.security.service.*;
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
    private BotLoginService botLoginService;

    @Resource
    private RefreshService refreshService;

    @Resource
    private LogoutService logoutService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @PostMapping("/login")
    public @ResponseBody
    SessionDto login(@Valid @RequestBody LoginInputDto input) {
        return loginService.login(input);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @PostMapping("/botLogin")
    public @ResponseBody
    SessionDto botLogin() {
        return botLoginService.login();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @PostMapping("/refresh")
    public @ResponseBody
    SessionDto refresh() {
        return refreshService.refresh();
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @PostMapping("/logout")
    public void logout() {
        logoutService.logout();
    }

}
