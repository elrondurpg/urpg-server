package com.pokemonurpg.member.service;

import com.pokemonurpg.security.service.SessionExpirationService;
import com.pokemonurpg.core.service.SystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionExpirationServiceTest {
    private final static Long CURRENT_TIME_MILLIS = 52342000L;

    @InjectMocks
    private SessionExpirationService sessionExpirationService;

    @Mock
    private SystemService systemService;

    @BeforeEach
    public void init() {
        when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME_MILLIS);
    }

    @Test
    public void expiredWhenCurrentTimeGreaterThanInput() {
        assertTrue(sessionExpirationService.isExpired(CURRENT_TIME_MILLIS / 1000 + 59));
    }

    @Test
    public void unexpiredWhenCurrentTimeLessThanInput() {
        assertFalse(sessionExpirationService.isExpired(CURRENT_TIME_MILLIS / 1000 + 61));
    }
}