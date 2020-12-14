package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.service.AuthenticationService;
import com.pokemonurpg.member.service.AuthorizationService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_00002;

@Aspect
@Configuration
public class AuthenticatedAspect {

    @Resource
    private AuthenticationService authenticationService;

    @Before("@annotation(com.pokemonurpg.core.security.annotation.Authenticated) && args(input)")
    public void before(AuthenticatedInputDto input) {
        SessionDto session = input.getSession();
        if (authenticationService.authenticate(session) == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_00002);
        }
    }
}
