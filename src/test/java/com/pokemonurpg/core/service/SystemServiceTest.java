package com.pokemonurpg.core.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SystemServiceTest {

    private SystemService systemService = new SystemService();

    @Test
    public void currentTimeMillis() {
        assertTrue(systemService.currentTimeMillis() > 0);
    }

}