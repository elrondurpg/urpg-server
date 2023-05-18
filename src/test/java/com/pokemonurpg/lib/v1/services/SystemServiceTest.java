package com.pokemonurpg.lib.v1.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemServiceTest {

    private SystemService systemService = new SystemService();

    @Test
    public void currentTimeMillis() {
        assertTrue(systemService.currentTimeMillis() > 0);
    }

}