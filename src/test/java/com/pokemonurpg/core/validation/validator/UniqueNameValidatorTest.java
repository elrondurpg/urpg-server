package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
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
        UniquelyNamedInputDto input = new MemberInputDto();
        assertTrue(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isValidWhenNoObjectExistsWithProvidedName() {
        UniquelyNamedInputDto input = new MemberInputDto();
        input.setName(NAME);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByName(NAME)).thenReturn(null);

        assertTrue(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isInvalidWhenObjectWithProvidedNameExistsButRequestDbidIsNull() {
        UniquelyNamedInputDto input = new MemberInputDto();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(null);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByName(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(WRONG_DBID);

        assertFalse(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isInvalidWhenObjectWithProvidedNameExistsButObjectDbidIsNotEqualToRequestDbid() {
        UniquelyNamedInputDto input = new MemberInputDto();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(REQUEST_DBID);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByName(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(WRONG_DBID);

        assertFalse(uniqueNameValidator.isValid(input, null));
    }

    @Test
    public void isValidWhenObjectWithProvidedNameExistsAndObjectDbidEqualsRequestDbid() {
        UniquelyNamedInputDto input = new MemberInputDto();
        input.setName(NAME);

        when(requestPathVariableService.findIntByName("dbid")).thenReturn(REQUEST_DBID);

        when(namedObjectServiceFactory.getServiceForClass(Member.class)).thenReturn(memberService);
        when(memberService.findByName(NAME)).thenReturn(MEMBER);
        when(MEMBER.getDbid()).thenReturn(REQUEST_DBID);

        assertTrue(uniqueNameValidator.isValid(input, null));
    }

}