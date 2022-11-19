package com.pokemonurpg.configuration.v1.gym.championownershipterm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.championownershipterm.input.ChampionOwnershipTermInputTestDto;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.repository.ChampionOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.gym.service.ChampionService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.test.RandomStringGenerator;

@ExtendWith(MockitoExtension.class)
public class ChampionOwnershipTermServiceTest {
    private final static String SLOT_NAME = RandomStringGenerator.generate();
    private final static String OWNER_NAME = RandomStringGenerator.generate();
    private final static Date OPEN_DATE = new Date();
    private final static Champion SLOT = new Champion();
    private final static Member OWNER = new Member();
    private final static ChampionOwnershipTerm MODEL = new ChampionOwnershipTerm();

    @InjectMocks
    private ChampionOwnershipTermService service;

    @Mock
    private ChampionOwnershipTermRepository repository;

    @Mock
    private ChampionService championService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownChampionService knownChampionService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ChampionOwnershipTerm.class, service.getModelClass());
    }

    @Test
    public void test_findBySlotAndOwnerAndOpenDate() {
        when(championService.findByName(SLOT_NAME)).thenReturn(SLOT);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);
        when(repository.findBySlotAndOwnerAndOpenDate(SLOT, OWNER, OPEN_DATE)).thenReturn(MODEL);
        assertEquals(MODEL, service.findBySlotAndOwnerAndOpenDate(SLOT_NAME, OWNER_NAME, OPEN_DATE));
    }

    @Test
    public void test_setKeyValues() {        
        ChampionOwnershipTermInputTestDto input = new ChampionOwnershipTermInputTestDto();

        when(championService.findByName(input.getSlot())).thenReturn(ChampionOwnershipTermInputTestDto.SLOT);
        when(memberService.findByNameExact(input.getOwner())).thenReturn(ChampionOwnershipTermInputTestDto.OWNER);

        service.setKeyValues(MODEL, input);

        assertEquals(ChampionOwnershipTermInputTestDto.SLOT, MODEL.getSlot());
        assertEquals(ChampionOwnershipTermInputTestDto.OWNER, MODEL.getOwner());
        assertEquals(input.getOpenDate(), MODEL.getOpenDate());
    }

    @Test
    public void test_updateBase() {
        ChampionOwnershipTermInputTestDto input = new ChampionOwnershipTermInputTestDto();

        service.updateBase(MODEL, input);
        assertEquals(input.getDraws(), MODEL.getDraws());
        assertEquals(input.getLosses(), MODEL.getLosses());
        assertEquals(input.getWins(), MODEL.getWins());
    }

    @Test
    public void test_updateAssociatedValues_AndBecomeCurrentOwner() {
        ChampionOwnershipTermInputTestDto input = new ChampionOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(true);
        MODEL.setSlot(SLOT);

        service.updateAssociatedValues(MODEL, input);

        verify(knownChampionService, times(1)).createForNameIfUnique(input.getOwner());
        verify(championService, times(1)).updateCurrentOwnerRecord(SLOT, MODEL);
    }

    @Test
    public void test_updateAssociatedValues_AndDontBecomeCurrentOwner() {
        ChampionOwnershipTermInputTestDto input = new ChampionOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(false);
        MODEL.setSlot(SLOT);

        service.updateAssociatedValues(MODEL, input);

        verify(knownChampionService, times(1)).createForNameIfUnique(input.getOwner());
        verify(championService, times(0)).updateCurrentOwnerRecord(ArgumentMatchers.any(), ArgumentMatchers.any());
    }

}