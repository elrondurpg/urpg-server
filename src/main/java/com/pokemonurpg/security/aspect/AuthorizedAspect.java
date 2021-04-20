package com.pokemonurpg.security.aspect;

import com.pokemonurpg.security.annotation.Authorized;
import com.pokemonurpg.security.service.AuthorizationService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

import static com.pokemonurpg.strings.ErrorStrings.ERROR_00002;

@Aspect
@Configuration
@Order(1)
public class AuthorizedAspect {

    @Resource
    private AuthorizationService authorizationService;

    @Before("@annotation(authorized)")
    public void before(Authorized authorized) {
        if (!authorizationService.isAuthorized(authorized.permission())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ERROR_00002);
        }
    }
}
