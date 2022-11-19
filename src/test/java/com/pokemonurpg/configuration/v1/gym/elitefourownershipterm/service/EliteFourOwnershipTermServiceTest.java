package com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.input.EliteFourOwnershipTermInputTestDto;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.repository.EliteFourOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;
import com.pokemonurpg.gym.models.EliteFour;
import com.pokemonurpg.gym.service.EliteFourService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.test.RandomStringGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class EliteFourOwnershipTermServiceTest {
    private final static String SLOT_NAME = RandomStringGenerator.generate();
    private final static String OWNER_NAME = RandomStringGenerator.generate();
    private final static Date OPEN_DATE = new Date();
    private final static EliteFour SLOT = new EliteFour();
    private final static Member OWNER = new Member();
    private final static EliteFourOwnershipTerm MODEL = new EliteFourOwnershipTerm();

    @InjectMocks
    private EliteFourOwnershipTermService service;

    @Mock
    private EliteFourOwnershipTermRepository repository;

    @Mock
    private EliteFourService eliteFourService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(EliteFourOwnershipTerm.class, service.getModelClass());
    }

    @Test
    public void test_findBySlotAndOwnerAndOpenDate() {
        when(eliteFourService.findByName(SLOT_NAME)).thenReturn(SLOT);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);
        when(repository.findBySlotAndOwnerAndOpenDate(SLOT, OWNER, OPEN_DATE)).thenReturn(MODEL);
        assertEquals(MODEL, service.findBySlotAndOwnerAndOpenDate(SLOT_NAME, OWNER_NAME, OPEN_DATE));
    }

    @Test
    public void test_setKeyValues() {        
        EliteFourOwnershipTermInputTestDto input = new EliteFourOwnershipTermInputTestDto();

        when(eliteFourService.findByName(input.getSlot())).thenReturn(EliteFourOwnershipTermInputTestDto.SLOT);
        when(memberService.findByNameExact(input.getOwner())).thenReturn(EliteFourOwnershipTermInputTestDto.OWNER);

        service.setKeyValues(MODEL, input);

        assertEquals(EliteFourOwnershipTermInputTestDto.SLOT, MODEL.getSlot());
        assertEquals(EliteFourOwnershipTermInputTestDto.OWNER, MODEL.getOwner());
        assertEquals(input.getOpenDate(), MODEL.getOpenDate());
    }

    @Test
    public void test_updateBase() {
        EliteFourOwnershipTermInputTestDto input = new EliteFourOwnershipTermInputTestDto();

        service.updateBase(MODEL, input);
        assertEquals(input.getDraws(), MODEL.getDraws());
        assertEquals(input.getLosses(), MODEL.getLosses());
        assertEquals(input.getWins(), MODEL.getWins());
    }

    @Test
    public void test_updateAssociatedValues_AndBecomeCurrentOwner() {
        EliteFourOwnershipTermInputTestDto input = new EliteFourOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(true);
        MODEL.setSlot(SLOT);

        service.updateAssociatedValues(MODEL, input);

        verify(knownEliteFourMemberService, times (1)).createForNameIfUnique(input.getOwner());
        verify(eliteFourService, times(1)).updateCurrentOwnerRecord(SLOT, MODEL);
    }

    @Test
    public void test_updateAssociatedValues_AndDontBecomeCurrentOwner() {
        EliteFourOwnershipTermInputTestDto input = new EliteFourOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(false);
        MODEL.setSlot(SLOT);

        service.updateAssociatedValues(MODEL, input);

        verify(knownEliteFourMemberService, times(1)).createForNameIfUnique(input.getOwner());
        verify(eliteFourService, times(0)).updateCurrentOwnerRecord(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

}