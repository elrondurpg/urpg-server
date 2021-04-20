package com.pokemonurpg.security.service;

import com.pokemonurpg.core.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionExpirationService {

    @Resource
    private SystemService systemService;

    public boolean isExpired(long expireTime) {
        return (systemService.currentTimeMillis() / 1000) >= expireTime - 60;
    }
}
