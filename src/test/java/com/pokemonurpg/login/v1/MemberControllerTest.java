package com.pokemonurpg.login.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerTest {
    private final static Session SESSION = mock(Session.class);

    @InjectMocks
    private MemberController memberController;

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
        LoginRequest input = new LoginRequest();
        when(loginService.login(input)).thenReturn(SESSION);
        assertEquals(SESSION, memberController.login(input, null));
    }

    @Test
    public void botLogin() {
        when(botLoginService.login()).thenReturn(SESSION);
        assertEquals(SESSION, memberController.login(null, MemberController.BOT_LOGIN_QUERY_PARAMETER));
    }

    @Test
    public void refresh() {
        Session input = new Session();
        when(refreshService.refresh()).thenReturn(SESSION);
        assertEquals(SESSION, memberController.refresh());
    }

    @Test
    public void logout() {
        memberController.logout();
        verify(logoutService, times(1)).logout();
    }

}