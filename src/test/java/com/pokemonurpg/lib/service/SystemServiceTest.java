package com.pokemonurpg.lib.service;

import com.pokemonurpg.lib.v1.service.SystemService;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemServiceTest {

    private SystemService systemService = new SystemService();

    @Test
    public void currentTimeMillis() {
        assertTrue(systemService.currentTimeMillis() > 0);
    }

}