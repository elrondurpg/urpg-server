package com.pokemonurpg.member.service;

import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.service.*;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.models.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Provider;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {
    private final static Permission         PERMISSION  = mock(Permission.class);
    private final static SessionDto         SESSION     = mock(SessionDto.class);
    private final static Set<Permission>    PERMISSIONS = Collections.singleton(PERMISSION);
    private final static String PERMISSION_NAME = "PERMISSION";
    private final static Member MEMBER = mock(Member.class);

    @InjectMocks
    private AuthorizationService authorizationService;
    
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    private SessionService sessionService = mock(SessionService.class);

    @BeforeEach
    public void init() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);
    }

    @Test
    public void failsWhenMemberIsNull() {
        when(sessionService.getAuthenticatedMember()).thenReturn(null);
        assertFalse(authorizationService.isAuthorized(PERMISSION_NAME));
    }

    @Test
    public void failsWhenMemberIsBanned() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.isBanned()).thenReturn(true);
        assertFalse(authorizationService.isAuthorized(PERMISSION_NAME));
    }

    @Test
    public void failsWhenMemberDoesntHavePermission() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.isBanned()).thenReturn(false);
        when(sessionService.getAuthenticatedPermissions()).thenReturn(Collections.emptySet());
        assertFalse(authorizationService.isAuthorized(PERMISSION_NAME));
    }

    @Test
    public void failsWhenMemberHasOnlyANullPermission() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.isBanned()).thenReturn(false);
        when(sessionService.getAuthenticatedPermissions()).thenReturn(Collections.singleton(null));
        assertFalse(authorizationService.isAuthorized(PERMISSION_NAME));
    }

    @Test
    public void failsWhenMemberHasOnlyAnIncorrectPermission() {
        when(PERMISSION.getName()).thenReturn("garbage");

        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.isBanned()).thenReturn(false);
        when(sessionService.getAuthenticatedPermissions()).thenReturn(PERMISSIONS);
        assertFalse(authorizationService.isAuthorized(PERMISSION_NAME));
    }

    @Test
    public void succeedsWhenMemberHasPermission() {
        when(PERMISSION.getName()).thenReturn(PERMISSION_NAME);

        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.isBanned()).thenReturn(false);
        when(sessionService.getAuthenticatedPermissions()).thenReturn(PERMISSIONS);
        assertTrue(authorizationService.isAuthorized(PERMISSION_NAME));
    }


    
}