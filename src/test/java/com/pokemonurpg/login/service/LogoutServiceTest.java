package com.pokemonurpg.login.service;

import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.v1.LogoutService;
import com.pokemonurpg.login.v1.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Provider;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogoutServiceTest {
    private final static Member MEMBER = mock(Member.class);

    @InjectMocks
    private LogoutService logoutService;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    @Mock
    private MemberService memberService;

    private SessionService sessionService = mock(SessionService.class);


    @Test
    public void logout() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        logoutService.logout();
        verify(memberService, times(1)).logout(MEMBER);
    }

    @Test
    public void logoutFailsWhenMemberNotFound() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);
        when(sessionService.getAuthenticatedMember()).thenReturn(null);
        logoutService.logout();
        verify(memberService, times(0)).logout(any());
    }

}