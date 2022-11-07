package com.pokemonurpg.lib.security.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class AllowAuthenticatedAuthorizationService implements AuthorizationService {

    @Override
    public boolean isAuthorized(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
