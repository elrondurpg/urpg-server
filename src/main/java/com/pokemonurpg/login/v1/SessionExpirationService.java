package com.pokemonurpg.login.v1;

import com.pokemonurpg.lib.v1.service.SystemService;
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
