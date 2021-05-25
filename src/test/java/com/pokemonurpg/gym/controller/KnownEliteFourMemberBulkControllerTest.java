package com.pokemonurpg.gym.controller;

import com.pokemonurpg.gym.input.KnownEliteFourMemberBulkInputDto;
import com.pokemonurpg.gym.service.KnownEliteFourMemberBulkService;
import com.pokemonurpg.gym.service.KnownEliteFourMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class KnownEliteFourMemberBulkControllerTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static KnownEliteFourMemberBulkInputDto INPUT = Mockito.mock(KnownEliteFourMemberBulkInputDto.class);

    @InjectMocks
    private KnownEliteFourMemberBulkController knownEliteFourMemberBulkController;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownEliteFourMemberBulkService knownEliteFourMemberBulkService;

    @Test
    public void findAllNames() {
        Mockito.when(knownEliteFourMemberService.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownEliteFourMemberBulkController.findAllNames());
    }

    @Test
    public void create() {
        Mockito.when(knownEliteFourMemberBulkService.update(INPUT)).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, knownEliteFourMemberBulkController.update(INPUT));
    }

}