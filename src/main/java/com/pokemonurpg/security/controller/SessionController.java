package com.pokemonurpg.security.controller;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.RegisterNewUser;
import com.pokemonurpg.lib.security.v1.AuthorizationType;
import com.pokemonurpg.lib.security.v1.CheckAuthorization;
import com.pokemonurpg.security.dto.RegistrationInputDto;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.security.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_ON_CLAIM;

@RestController
@RequestMapping("/session")
@CrossOrigin
@Validated
public class SessionController {

    @Resource
    private RegistrationService registrationService;

    @Resource
    private LoginService loginService;

    @Resource
    private BotLoginService botLoginService;

    @Resource
    private RefreshService refreshService;

    @Resource
    private LogoutService logoutService;

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @PostMapping("/claimKnownName")
    public @ResponseBody
    ResponseEntity claimKnownName(@Valid @RequestBody RegistrationInputDto input) {
        try {
            registrationService.claimKnownName(input);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_CLAIM);
        }
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @Validated({ObjectCreation.class, RegisterNewUser.class})
    @PostMapping("/register/new")
    public @ResponseBody
    SessionDto registerNew(@Valid @RequestBody RegistrationInputDto input) {
        return registrationService.registerNew(input);
    }

    @CheckAuthorization(authorizationType = AuthorizationType.ALLOW_ALL)
    @Validated(ObjectCreation.class)
    @PostMapping("/register/vet")
    public @ResponseBody
    SessionDto registerVet(@Valid @RequestBody RegistrationInputDto input) {
        return registrationService.registerVet(input);
    }

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
