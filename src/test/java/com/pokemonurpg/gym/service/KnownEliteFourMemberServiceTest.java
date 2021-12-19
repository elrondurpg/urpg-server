package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownEliteFourMember;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;
import com.pokemonurpg.gym.repository.KnownEliteFourMemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KnownEliteFourMemberServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static String NEW_NAME = "NEW_NAME";
    private final static String OLD_NAME = "OLD_NAME";

    private KnownEliteFourMember knownEliteFourMember = new KnownEliteFourMember();

    @InjectMocks
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownEliteFourMemberRepository knownEliteFourMemberRepository;

    @Captor
    ArgumentCaptor<KnownEliteFourMember> captor;

    @Test
    public void findAllNames() {
        when(knownEliteFourMemberRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownEliteFourMemberService.findAllNames());
    }

    @Test
    public void findByName() {
        when(knownEliteFourMemberRepository.findByName(NAME)).thenReturn(knownEliteFourMember);
        assertEquals(knownEliteFourMember, knownEliteFourMemberService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(knownEliteFourMemberRepository.findFirstByNameStartingWith(NAME)).thenReturn(knownEliteFourMember);
        assertEquals(knownEliteFourMember, knownEliteFourMemberService.findByName(NAME));
    }

    @Test
    public void create() {
        when(knownEliteFourMemberRepository.findByName(NAME)).thenReturn(null);
        knownEliteFourMemberService.create(NAME);

        verify(knownEliteFourMemberRepository, times(1)).save(captor.capture());
        KnownEliteFourMember savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }

    /*@Test
    public void createByInputDto() {
        KnownEliteFourMemberInputDto input = new KnownEliteFourMemberInputDto();
        input.setName(NAME);

        when(knownEliteFourMemberRepository.findByName(NAME)).thenReturn(null);
        knownEliteFourMemberService.update(input);

        verify(knownEliteFourMemberRepository, times(1)).save(captor.capture());
        KnownEliteFourMember savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }*/

    /*@Test
    public void updateDelete() {
        KnownEliteFourMemberInputDto input = new KnownEliteFourMemberInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(knownEliteFourMemberRepository.findByName(NAME)).thenReturn(knownEliteFourMember);

        knownEliteFourMemberService.update(input);
        verify(knownEliteFourMemberRepository, times(1)).delete(knownEliteFourMember);
    }*/

    /*@Test
    public void update_ByNewAndOldName_Succeeds() {
        when(knownEliteFourMemberRepository.findByName(OLD_NAME)).thenReturn(knownEliteFourMember);
        knownEliteFourMemberService.update(NEW_NAME, OLD_NAME);
        assertEquals(NEW_NAME, knownEliteFourMember.getName());
        verify(knownEliteFourMemberRepository, times(1)).save(captor.capture());
        KnownEliteFourMember savedObject = captor.getValue();
        assertEquals(knownEliteFourMember, savedObject);
    }*/
}