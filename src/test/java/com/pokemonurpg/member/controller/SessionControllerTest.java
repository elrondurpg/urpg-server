package com.pokemonurpg.member.controller;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.input.LoginInputDto;
import com.pokemonurpg.member.service.AuthenticationService;
import com.pokemonurpg.member.service.LoginService;
import com.pokemonurpg.member.service.RefreshService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerTest {
    private final static SessionDto SESSION = mock(SessionDto.class);

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private LoginService loginService;

    @Mock
    private RefreshService refreshService;

    @Test
    public void login() {
        LoginInputDto input = new LoginInputDto();
        when(loginService.login(input)).thenReturn(SESSION);
        assertEquals(SESSION, sessionController.login(input));
    }

    @Test
    public void refresh() {
        SessionDto input = new SessionDto();
        when(refreshService.refresh()).thenReturn(SESSION);
        assertEquals(SESSION, sessionController.refresh());
    }

}