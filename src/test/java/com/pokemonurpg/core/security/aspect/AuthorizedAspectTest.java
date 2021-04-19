package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.annotation.Authorized;

import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.service.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizedAspectTest {
    private final static String PERMISSION = "WRITE";

    @InjectMocks
    private AuthorizedAspect authorizedAspect;

    @Mock
    private AuthorizationService authorizationService;

    private Authorized constraint = mock(Authorized.class);

    @Before
    public void init() {
        when(constraint.permission()).thenReturn(PERMISSION);
    }

    @Test(expected = ResponseStatusException.class)
    public void throwsErrorWhenMemberNotAuthorized() {
        when(authorizationService.isAuthorized(constraint.permission())).thenReturn(false);
        authorizedAspect.before(constraint);
    }

    @Test
    public void noErrorWhenMemberAuthorized() {
        when(authorizationService.isAuthorized(constraint.permission())).thenReturn(true);
        authorizedAspect.before(constraint);
    }

}