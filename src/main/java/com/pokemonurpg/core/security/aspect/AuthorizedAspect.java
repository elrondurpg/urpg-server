package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.security.dto.SessionDto;
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
public class AuthorizedAspect {

    @Resource
    private AuthorizationService authorizationService;

    @Before("@annotation(authorized) && args(input,..)")
    public void before(Authorized authorized, AuthenticatedInputDto input) {
        SessionDto session = input.getSession();
        if (!authorizationService.isAuthorized(session, authorized.permission())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_00002);
        }
    }
}
