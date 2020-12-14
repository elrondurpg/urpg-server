package com.pokemonurpg.core.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemServiceTest {

    private SystemService systemService = new SystemService();

    @Test
    public void currentTimeMillis() {
        assertTrue(systemService.currentTimeMillis() > 0);
    }

}