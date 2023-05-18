package com.pokemonurpg.lib.v1.services;

import org.springframework.stereotype.Service;

@Service
public class SystemService {

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
