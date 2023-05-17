package com.pokemonurpg.lib.service;

import org.springframework.stereotype.Service;

@Service
public class SystemService {

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
