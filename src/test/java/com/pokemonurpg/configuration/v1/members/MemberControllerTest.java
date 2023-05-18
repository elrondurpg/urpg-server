package com.pokemonurpg.configuration.v1.members;

import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.members.MemberController;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberInputDto;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.login.v1.AuthorizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.pokemonurpg.lib.v1.strings.PermissionNames.WRITE_MEMBER_PERMISSION;
import static com.pokemonurpg.lib.v1.strings.PermissionNames.WRITE_ROLE_PERMISSION;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static Boolean BOT = true;

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @Mock
    private AuthorizationService authorizationService;

    private Member member = new Member();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(memberService.findNamesBy(NAME, BOT)).thenReturn(names);
        assertEquals(names, memberController.findNamesBy(NAME, BOT));
    }

    @Test
    public void findByName_ReturnsRoles_WhenAuthorized() {
        when(memberService.findByName(NAME)).thenReturn(member);
        when(authorizationService.isAuthorized(WRITE_ROLE_PERMISSION)).thenReturn(true);

        MappingJacksonValue result = memberController.findByName(NAME);
        assertEquals(member, result.getValue());
        assertEquals(View.MemberView.Secure.class, result.getSerializationView());
    }

    @Test
    public void findByName_DoesNotReturnRoles_WhenNotAuthorized() {
        when(memberService.findByName(NAME)).thenReturn(member);
        when(authorizationService.isAuthorized(WRITE_ROLE_PERMISSION)).thenReturn(false);

        MappingJacksonValue result = memberController.findByName(NAME);
        assertEquals(member, result.getValue());
        assertEquals(View.MemberView.Summary.class, result.getSerializationView());
    }

    @Test
    public void create() {
        MemberInputDto input = new MemberInputDto();
        input.setName(NAME);
        when(memberService.create(input)).thenReturn(member);
        assertEquals(member, memberController.create(input));
    }

    @Test
    public void update_ReturnsRoles_WhenAuthorized() {
        MemberInputDto input = new MemberInputDto();
        when(memberService.update(input, DBID)).thenReturn(member);
        when(authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)).thenReturn(true);

        MappingJacksonValue result = memberController.update(input, DBID);
        assertEquals(member, result.getValue());
        assertEquals(View.MemberView.Secure.class, result.getSerializationView());
    }

    @Test
    public void update_DoesNotReturnRoles_WhenNotAuthorized() {
        MemberInputDto input = new MemberInputDto();
        when(memberService.update(input, DBID)).thenReturn(member);
        when(authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)).thenReturn(false);

        MappingJacksonValue result = memberController.update(input, DBID);
        assertEquals(member, result.getValue());
        assertEquals(View.MemberView.Summary.class, result.getSerializationView());
    }

    @Test
    public void delete() {
        ResponseEntity response = memberController.delete(DBID);
        verify(memberService, times(1)).delete(DBID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void delete_ThrowsInternalServerError_OnException() {
        doThrow(new IllegalStateException()).when(memberService).delete(DBID);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> memberController.delete(DBID));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }
}