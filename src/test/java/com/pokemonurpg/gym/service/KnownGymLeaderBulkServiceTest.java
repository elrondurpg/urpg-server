package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownGymLeaderBulkInputDto;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KnownGymLeaderBulkServiceTest {
    private final static List<String> LEADER_NAMES = new ArrayList<>();

    @InjectMocks
    private KnownGymLeaderBulkService knownGymLeaderBulkService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Test
    public void update() {
        KnownGymLeaderInputDto input1 = new KnownGymLeaderInputDto();
        KnownGymLeaderInputDto input2 = new KnownGymLeaderInputDto();
        List<KnownGymLeaderInputDto> list = Arrays.asList(input1, input2);

        KnownGymLeaderBulkInputDto bulkInput = new KnownGymLeaderBulkInputDto();
        bulkInput.setLeaders(list);

        when(knownGymLeaderService.findAllNames()).thenReturn(LEADER_NAMES);

        List<String> leaderNames = knownGymLeaderBulkService.update(bulkInput);
        assertEquals(LEADER_NAMES, leaderNames);
        verify(knownGymLeaderService).update(input1);
        verify(knownGymLeaderService).update(input2);
    }

}