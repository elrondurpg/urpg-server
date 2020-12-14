package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.core.security.service.OAuthService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.repository.PermissionRepository;
import com.pokemonurpg.member.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void failsWhenMemberIsNull() {
        when(authenticationService.authenticate(SESSION)).thenReturn(null);
        assertFalse(authorizationService.isAuthorized(SESSION, PERMISSION));
    }

    @Test
    public void failsWhenMemberIsBanned() {
        when(authenticationService.authenticate(SESSION)).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(true);
        assertFalse(authorizationService.isAuthorized(SESSION, PERMISSION));
    }

    @Test
    public void failsWhenMemberDoesntHavePermission() {
        when(authenticationService.authenticate(SESSION)).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        when(MEMBER.getRoles()).thenReturn(Collections.emptySet());
        assertFalse(authorizationService.isAuthorized(SESSION, PERMISSION));
    }

    @Test
    public void failsWhenMemberHasRolesButNotPermission() {
        when(authenticationService.authenticate(SESSION)).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(new Role()));
        assertFalse(authorizationService.isAuthorized(SESSION, PERMISSION));
    }

    @Test
    public void failsWhenMemberHasPermissionsButNoneMatch() {
        when(authenticationService.authenticate(SESSION)).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);
        Permission perm = new Permission();
        perm.setName("sffdsafdsafahiw");
        Role role = new Role();
        role.setPermissions(Collections.singleton(perm));
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(role));
        assertFalse(authorizationService.isAuthorized(SESSION, PERMISSION));
    }

    @Test
    public void succeedsWhenMemberHasPermission() {
        when(authenticationService.authenticate(SESSION)).thenReturn(MEMBER);
        when(MEMBER.getBanned()).thenReturn(false);

        Permission perm = new Permission();
        perm.setName(PERMISSION);

        Role role = new Role();
        role.setPermissions(Collections.singleton(perm));
        when(MEMBER.getRoles()).thenReturn(Collections.singleton(role));

        assertTrue(authorizationService.isAuthorized(SESSION, PERMISSION));
    }


    
}