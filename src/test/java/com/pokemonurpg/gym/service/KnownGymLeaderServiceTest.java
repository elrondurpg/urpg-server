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
    private final static String OLD_NAME = "OLD_NAME";
    private final static String NEW_NAME = "NEW_NAME";

    @InjectMocks
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private KnownGymLeaderRepository knownGymLeaderRepository;

    @Captor
    ArgumentCaptor<KnownGymLeader> captor;

    private KnownGymLeader knownGymLeader = new KnownGymLeader();

    @Test
    public void findAllNames() {
        when(knownGymLeaderRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownGymLeaderService.findAllNames());
    }

    @Test
    public void findByName() {
        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(knownGymLeader);
        assertEquals(knownGymLeader, knownGymLeaderService.findByName(NAME));
    }

    @Test
    public void findFirstByNameStartingWith() {
        when(knownGymLeaderRepository.findFirstByNameStartingWith(NAME)).thenReturn(knownGymLeader);
        assertEquals(knownGymLeader, knownGymLeaderService.findByName(NAME));
    }

    @Test
    public void create() {
        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(null);
        knownGymLeaderService.create(NAME);

        verify(knownGymLeaderRepository, times(1)).save(captor.capture());
        KnownGymLeader savedObject = captor.getValue();
        assertEquals(NAME, savedObject.getName());
    }
/*
    @Test
    public void createByInputDto() {
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

        when(knownGymLeaderRepository.findByName(NAME)).thenReturn(knownGymLeader);

        knownGymLeaderService.update(input);
        verify(knownGymLeaderRepository, times(1)).delete(knownGymLeader);
    }

    @Test
    public void update_ByNewAndOldName_Succeeds() {
        when(knownGymLeaderRepository.findByName(OLD_NAME)).thenReturn(knownGymLeader);
        knownGymLeaderService.update(NEW_NAME, OLD_NAME);
        assertEquals(NEW_NAME, knownGymLeader.getName());
        verify(knownGymLeaderRepository, times(1)).save(captor.capture());
        KnownGymLeader savedObject = captor.getValue();
        assertEquals(knownGymLeader, savedObject);
    }*/
}