package com.pokemonurpg.member.service;

import com.pokemonurpg.security.dto.SessionDto;
import com.pokemonurpg.security.service.*;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.models.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Provider;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationServiceTest {
    private final static SessionDto SESSION = mock(SessionDto.class);
    private final static String PERMISSION = "PERMISSION";
    private final static Member MEMBER = mock(Member.class);

    @InjectMocks
    private AuthorizationService authorizationService;
    
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private Provider<SessionService> sessionServiceProvider;

    private SessionService sessionService = mock(SessionService.class);

    @Before
    public void init() {
        when(sessionServiceProvider.get()).thenReturn(sessionService);
    }

    @Test
    public void failsWhenMemberIsNull() {
        when(sessionService.getAuthenticatedMember()).thenReturn(null);
        assertFalse(authorizationService.isAuthorized(PERMISSION));
    }

    @Test
    public void failsWhenMemberIsBanned() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(true);
        assertFalse(authorizationService.isAuthorized(PERMISSION));
    }

    @Test
    public void failsWhenMemberDoesntHavePermission() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        when(MEMBER.getRoles()).thenReturn(Collections.emptySet());
        assertFalse(authorizationService.isAuthorized(PERMISSION));
    }

    @Test
    public void failsWhenMemberHasRolesButNotPermission() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(new Role()));
        assertFalse(authorizationService.isAuthorized(PERMISSION));
    }

    @Test
    public void failsWhenMemberHasPermissionsButNoneMatch() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        Permission perm = new Permission();
        perm.setName("sffdsafdsafahiw");
        Role role = new Role();
        role.setPermissions(Collections.singleton(perm));
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(role));
        assertFalse(authorizationService.isAuthorized(PERMISSION));
    }

    @Test
    public void succeedsWhenMemberHasPermission() {
        when(sessionService.getAuthenticatedMember()).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);

        Permission perm = new Permission();
        perm.setName(PERMISSION);

        Role role = new Role();
        role.setPermissions(Collections.singleton(perm));
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(role));

        assertTrue(authorizationService.isAuthorized(PERMISSION));
    }


    
}