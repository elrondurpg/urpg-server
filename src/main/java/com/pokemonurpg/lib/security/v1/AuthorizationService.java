package com.pokemonurpg.lib.security.v1;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationService {
    boolean isAuthorized(HttpServletRequest request);
}
