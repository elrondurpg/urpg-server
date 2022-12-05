package com.pokemonurpg.configuration.v1.gym.gymownershipterm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.gymownershipterm.input.GymOwnershipTermInputTestDto;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.repository.GymOwnershipTermRepository;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.service.LeagueService;
import com.pokemonurpg.configuration.v1.item.service.ItemService;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.test.RandomStringGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class GymOwnershipTermServiceTest {
    private final static String GYM_NAME = RandomStringGenerator.generate();
    private final static String OWNER_NAME = RandomStringGenerator.generate();
    private final static Date OPEN_DATE = new Date();
    private final static Gym GYM = new Gym();
    private final static Member OWNER = new Member();
    private final static GymOwnershipTerm MODEL = new GymOwnershipTerm();

    @InjectMocks
    private GymOwnershipTermService service;

    @Mock
    private GymOwnershipTermRepository repository;

    @Mock
    private GymService gymService;

    @Mock
    private LeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(GymOwnershipTerm.class, service.getModelClass());
    }

    @Test
    public void test_findByGymAndOwnerAndOpenDate() {
        when(gymService.findByName(GYM_NAME)).thenReturn(GYM);
        when(memberService.findByNameExact(OWNER_NAME)).thenReturn(OWNER);
        when(repository.findByGymAndOwnerAndOpenDate(GYM, OWNER, OPEN_DATE)).thenReturn(MODEL);
        assertEquals(MODEL, service.findByGymAndOwnerAndOpenDate(GYM_NAME, OWNER_NAME, OPEN_DATE));
    }

    @Test
    public void test_setKeyValues() {        
        GymOwnershipTermInputTestDto input = new GymOwnershipTermInputTestDto();

        when(gymService.findByName(input.getGym())).thenReturn(GymOwnershipTermInputTestDto.GYM);
        when(memberService.findByNameExact(input.getOwner())).thenReturn(GymOwnershipTermInputTestDto.OWNER);

        service.setKeyValues(MODEL, input);

        assertEquals(GymOwnershipTermInputTestDto.GYM, MODEL.getGym());
        assertEquals(GymOwnershipTermInputTestDto.OWNER, MODEL.getOwner());
        assertEquals(input.getOpenDate(), MODEL.getOpenDate());
    }

    @Test
    public void test_updateBase() {
        GymOwnershipTermInputTestDto input = new GymOwnershipTermInputTestDto();

        service.updateBase(MODEL, input);
        assertEquals(input.getDraws(), MODEL.getDraws());
        assertEquals(input.getLosses(), MODEL.getLosses());
        assertEquals(input.getWins(), MODEL.getWins());
    }

    @Test
    public void test_updateEmbeddedValues() {
        GymOwnershipTermInputTestDto input = new GymOwnershipTermInputTestDto();

        when(gymLeagueService.findByName(input.getLeague())).thenReturn(GymOwnershipTermInputTestDto.LEAGUE);
        when(itemService.findByName(input.getTm())).thenReturn(GymOwnershipTermInputTestDto.ITEM);

        service.updateEmbeddedValues(MODEL, input);

        assertEquals(GymOwnershipTermInputTestDto.LEAGUE, MODEL.getLeague());
        assertEquals(GymOwnershipTermInputTestDto.ITEM, MODEL.getTm());
    }

    @Test
    public void test_updateAssociatedValues_AndBecomeCurrentOwner() {
        GymOwnershipTermInputTestDto input = new GymOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(true);
        MODEL.setGym(GYM);

        service.updateAssociatedValues(MODEL, input);

        verify(knownGymLeaderService, times(1)).createForNameIfUnique(input.getOwner());
        verify(gymService, times(1)).updateCurrentOwnerRecord(GYM, MODEL);
    }

    @Test
    public void test_updateAssociatedValues_AndDontBecomeCurrentOwner() {
        GymOwnershipTermInputTestDto input = new GymOwnershipTermInputTestDto();
        input.setBecomeCurrentOwner(false);
        MODEL.setGym(GYM);

        service.updateAssociatedValues(MODEL, input);

        verify(knownGymLeaderService, times(1)).createForNameIfUnique(input.getOwner());
        verify(gymService, times(0)).updateCurrentOwnerRecord(ArgumentMatchers.any(), ArgumentMatchers.any());
    }
}