package com.pokemonurpg.core.security.aspect;

import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.security.dto.SessionDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.AuthenticationService;
import com.pokemonurpg.member.service.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatedAspectTest {

    @InjectMocks
    private AuthenticatedAspect authenticatedAspect;

    @Mock
    private AuthenticationService authenticationService;

    private SessionDto dto = new SessionDto();
    private AuthenticatedInputDto inputDto = new AuthenticatedInputDto();

    @Before
    public void init() {
        inputDto.setSession(dto);
    }

    @Test
    public void authenticatedUserThrowsNoError() {
        when(authenticationService.authenticate(dto)).thenReturn(new Member());
        authenticatedAspect.before(inputDto);
    }

    @Test(expected = ResponseStatusException.class)
    public void unauthenticatedUserThrowsError() {
        when(authenticationService.authenticate(dto)).thenReturn(null);
        authenticatedAspect.before(inputDto);
    }

}