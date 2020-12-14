package com.pokemonurpg.stats.service;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.repository.GymRepository;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import com.pokemonurpg.stats.models.EarnedBadge;
import com.pokemonurpg.stats.repository.EarnedBadgeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EarnedBadgeServiceTest {
    private final static Integer GYM_DBID = 432;
    private final static Member MEMBER = mock(Member.class);
    private final static Gym GYM = mock(Gym.class);
    private final static EarnedBadge EXISTING_RECORD = mock(EarnedBadge.class);

    @InjectMocks
    private EarnedBadgeService earnedBadgeService;

    @Mock
    private EarnedBadgeRepository earnedBadgeRepository;

    @Mock
    private GymService gymService;

    @Test(expected = ResponseStatusException.class)
    public void failsWhenGymNotFound() {
        when(gymService.findByDbid(GYM_DBID)).thenReturn(null);

        EarnedBadgeInputDto input = new EarnedBadgeInputDto();
        input.setGymDbid(GYM_DBID);

        earnedBadgeService.update(MEMBER, input);
    }

    @Test
    public void createsNewEarnedBadge() {
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(earnedBadgeRepository.findByMemberAndGym(MEMBER, GYM)).thenReturn(null);

        EarnedBadgeInputDto input = new EarnedBadgeInputDto();
        input.setGymDbid(GYM_DBID);

        earnedBadgeService.update(MEMBER, input);
        verify(earnedBadgeRepository, times(1)).save(Matchers.any(EarnedBadge.class));
    }

    @Test
    public void updatesExistingRecord() {
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(earnedBadgeRepository.findByMemberAndGym(MEMBER, GYM)).thenReturn(EXISTING_RECORD);

        EarnedBadgeInputDto input = new EarnedBadgeInputDto();
        input.setGymDbid(GYM_DBID);

        earnedBadgeService.update(MEMBER, input);
        verify(EXISTING_RECORD, times(1)).update(input);
        verify(earnedBadgeRepository, times(1)).save(EXISTING_RECORD);
    }

    @Test
    public void deletesExistingRecord() {
        when(gymService.findByDbid(GYM_DBID)).thenReturn(GYM);
        when(earnedBadgeRepository.findByMemberAndGym(MEMBER, GYM)).thenReturn(EXISTING_RECORD);

        EarnedBadgeInputDto input = new EarnedBadgeInputDto();
        input.setGymDbid(GYM_DBID);
        input.setDelete(true);

        earnedBadgeService.update(MEMBER, input);
        verify(earnedBadgeRepository, times(1)).delete(EXISTING_RECORD);
    }

}