package com.pokemonurpg.core.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AuthorizationHeaderService {
    @Resource
    private RequestHeaderService requestHeaderService;

    private String username = null;
    private String token = null;

    public void getUser() {
        String header = requestHeaderService.findByName("authorization");
        if (header != null) {

        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
