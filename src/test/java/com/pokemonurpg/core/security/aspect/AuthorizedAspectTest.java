package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.annotation.Authorized;
import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
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
    private AuthenticatedInputDto inputDto = mock(AuthenticatedInputDto.class);
    private SessionDto sessionDto = mock(SessionDto.class);

    @Before
    public void init() {
        when(constraint.permission()).thenReturn(PERMISSION);
        when(inputDto.getSession()).thenReturn(sessionDto);
    }

    @Test(expected = ResponseStatusException.class)
    public void throwsErrorWhenMemberNotAuthorized() throws IllegalAccessException {
        when(authorizationService.isAuthorized(sessionDto, constraint.permission())).thenReturn(false);
        authorizedAspect.before(constraint, inputDto);
    }

    @Test
    public void noErrorWhenMemberAuthorized() throws IllegalAccessException {
        when(authorizationService.isAuthorized(sessionDto, constraint.permission())).thenReturn(true);
        authorizedAspect.before(constraint, inputDto);
    }

}