package com.pokemonurpg.configuration.v1.elitefourmembers;

import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourMemberRepository;
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
public class EliteFourMemberSlotMemberServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static String NEW_NAME = "NEW_NAME";
    private final static String OLD_NAME = "OLD_NAME";

    private EliteFourMember eliteFourMember = new EliteFourMember();

    @InjectMocks
    private EliteFourMemberService eliteFourMemberService;

    @Mock
    private EliteFourMemberRepository eliteFourMemberRepository;

    @Captor
    ArgumentCaptor<EliteFourMember> captor;

    @Test
    public void findAllNames() {
        when(eliteFourMemberRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, eliteFourMemberService.findAllNames());
    }

    @Test
    public void findByName() {
        when(eliteFourMemberRepository.findByName(NAME)).thenReturn(eliteFourMember);
        assertEquals(eliteFourMember, eliteFourMemberService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(eliteFourMemberRepository.findFirstByNameStartingWith(NAME)).thenReturn(eliteFourMember);
        assertEquals(eliteFourMember, eliteFourMemberService.findByName(NAME));
    }

    @Test
    public void create() {
        when(eliteFourMemberRepository.findByName(NAME)).thenReturn(null);
        eliteFourMemberService.create(NAME);

        verify(eliteFourMemberRepository, times(1)).save(captor.capture());
        EliteFourMember savedObject = captor.getValue();
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