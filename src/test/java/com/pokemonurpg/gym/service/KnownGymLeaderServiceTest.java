package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;
import com.pokemonurpg.gym.repository.KnownGymLeaderRepository;
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
public class KnownGymLeaderServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static String NAME = "NAME";
    private final static KnownGymLeader KNOWN_GYM_LEADER = mock(KnownGymLeader.class);

    @InjectMocks
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private KnownGymLeaderRepository knownGymLeaderRepository;

    @Captor
    ArgumentCaptor<KnownGymLeader> captor;

    @Test
    public void findAllNames() {
        when(knownGymLeaderRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownGymLeaderService.findAllNames());
    }

    @Test
    public void findByName() {
        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(KNOWN_GYM_LEADER);
        assertEquals(KNOWN_GYM_LEADER, knownGymLeaderService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(knownGymLeaderRepository.findFirstByNameStartingWith(NAME)).thenReturn(KNOWN_GYM_LEADER);
        assertEquals(KNOWN_GYM_LEADER, knownGymLeaderService.findByName(NAME));
    }

    @Test
    public void create() {
        KnownGymLeaderInputDto input = new KnownGymLeaderInputDto();
        input.setName(NAME);

        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(null);
        knownGymLeaderService.update(input);

        verify(knownGymLeaderRepository, times(1)).save(captor.capture());
        KnownGymLeader savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }

    @Test
    public void updateDelete() {
        KnownGymLeaderInputDto input = new KnownGymLeaderInputDto();
        input.setName(NAME);
        input.setDelete(true);

        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(KNOWN_GYM_LEADER);

        knownGymLeaderService.update(input);
        verify(knownGymLeaderRepository, times(1)).delete(KNOWN_GYM_LEADER);
    }
}