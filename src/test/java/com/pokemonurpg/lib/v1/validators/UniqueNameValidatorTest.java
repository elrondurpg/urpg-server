package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;
import com.pokemonurpg.lib.v1.services.NamedObjectServiceFactory;
import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.UniqueName;
import com.pokemonurpg.configuration.v1.members.MemberRequest;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.configuration.v1.members.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@UniqueName(type = Member.class)
public class UniqueNameValidatorTest {
    private final static String NAME = "NAME";
    private final static Member MEMBER = mock(Member.class);
    private final static Integer REQUEST_DBID = 432432;
    private final static Integer WRONG_DBID = 3242;

    @InjectMocks
    private UniqueNameValidator uniqueNameValidator;

    @Mock
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Mock
    private RequestPathVariableService requestPathVariableService;

    private MemberService memberService = mock(MemberService.class);

    @Before
    public void init() {
        uniqueNameValidator.initialize(this.getClass().getAnnotation(UniqueName.class));
    }

    @Test
    public void isValidWhenInputNull() {
        assertTrue(uniqueNameValidator.isValid(null, null));
    }

    @Test
    public void isValidWhenInputNameNull() {
        UniquelyNamedRequest input = new MemberRequest();
        assertTrue(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isValidWhenNoObjectExistsWithProvidedName() {
        UniquelyNamedRequest input = new MemberRequest();
        input.setName(NAME);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByNameExact(NAME)).thenReturn(null);

        assertTrue(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isInvalidWhenObjectWithProvidedNameExistsButRequestDbidIsNull() {
        UniquelyNamedRequest input = new MemberRequest();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(null);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByNameExact(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(WRONG_DBID);

        assertFalse(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isInvalidWhenObjectWithProvidedNameExistsButObjectDbidIsNotEqualToRequestDbid() {
        UniquelyNamedRequest input = new MemberRequest();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(REQUEST_DBID);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByNameExact(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(WRONG_DBID);

        assertFalse(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isValidWhenObjectWithProvidedNameExistsAndObjectDbidEqualsRequestDbid() {
        UniquelyNamedRequest input = new MemberRequest();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(REQUEST_DBID);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByNameExact(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(REQUEST_DBID);

        assertTrue(uniqueNameValidator.isValid(input, null));
    }

}