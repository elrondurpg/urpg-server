package com.pokemonurpg.lib.security.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class DenyAllAuthorizationService implements AuthorizationService {

    @Override
    public boolean isAuthorized(HttpServletRequest request) {
        return false;
    }
    
}
