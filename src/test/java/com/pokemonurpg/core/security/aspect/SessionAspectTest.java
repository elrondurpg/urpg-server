package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.service.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Provider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionAspectTest {

    @InjectMocks
    private SessionAspect sessionAspect;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    private SessionService sessionService = mock(SessionService.class);

    @Test
    public void createSession() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);
        sessionAspect.createSession();
        verify(sessionService, times(1)).createSession();
    }

}