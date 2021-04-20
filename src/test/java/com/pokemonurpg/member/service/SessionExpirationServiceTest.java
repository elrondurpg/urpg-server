package com.pokemonurpg.member.service;

import com.pokemonurpg.core.service.SystemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionExpirationServiceTest {
    private final static Long CURRENT_TIME_MILLIS = 52342000L;

    @InjectMocks
    private SessionExpirationService sessionExpirationService;

    @Mock
    private SystemService systemService;

    @Before
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