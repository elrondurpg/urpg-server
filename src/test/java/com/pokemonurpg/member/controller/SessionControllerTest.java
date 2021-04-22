package com.pokemonurpg.member.controller;

import com.pokemonurpg.security.controller.SessionController;
import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.dto.LoginInputDto;
import com.pokemonurpg.security.service.BotLoginService;
import com.pokemonurpg.security.service.LoginService;
import com.pokemonurpg.security.service.LogoutService;
import com.pokemonurpg.security.service.RefreshService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerTest {
    private final static SessionDto SESSION = mock(SessionDto.class);

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private LoginService loginService;

    @Mock
    private BotLoginService botLoginService;

    @Mock
    private RefreshService refreshService;

    @Mock
    private LogoutService logoutService;

    @Test
    public void login() {
        LoginInputDto input = new LoginInputDto();
        when(loginService.login(input)).thenReturn(SESSION);
        assertEquals(SESSION, sessionController.login(input));
    }

    @Test
    public void botLogin() {
        when(botLoginService.login()).thenReturn(SESSION);
        assertEquals(SESSION, sessionController.botLogin());
    }

    @Test
    public void refresh() {
        SessionDto input = new SessionDto();
        when(refreshService.refresh()).thenReturn(SESSION);
        assertEquals(SESSION, sessionController.refresh());
    }

    @Test
    public void logout() {
        sessionController.logout();
        verify(logoutService, times(1)).logout();
    }

}