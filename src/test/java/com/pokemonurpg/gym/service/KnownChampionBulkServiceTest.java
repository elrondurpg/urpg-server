package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownChampionBulkInputDto;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
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
public class KnownChampionBulkServiceTest {
    private final static List<String> CHAMPION_NAMES = new ArrayList<>();

    @InjectMocks
    private KnownChampionBulkService knownChampionBulkService;

    @Mock
    private KnownChampionService knownChampionService;

    @Test
    public void update() {
        KnownChampionInputDto input1 = new KnownChampionInputDto();
        KnownChampionInputDto input2 = new KnownChampionInputDto();
        List<KnownChampionInputDto> list = Arrays.asList(input1, input2);

        KnownChampionBulkInputDto bulkInput = new KnownChampionBulkInputDto();
        bulkInput.setChampions(list);

        when(knownChampionService.findAllNames()).thenReturn(CHAMPION_NAMES);

        List<String> leaderNames = knownChampionBulkService.update(bulkInput);
        assertEquals(CHAMPION_NAMES, leaderNames);
        verify(knownChampionService).update(input1);
        verify(knownChampionService).update(input2);
    }

}