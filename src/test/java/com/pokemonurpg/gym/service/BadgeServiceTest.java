package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.BadgeInputDto;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.repository.BadgeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BadgeServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private BadgeService badgeService;

    @Mock
    private BadgeRepository badgeRepository;

    private Badge badge = new Badge();

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(badgeRepository.findAllNames()).thenReturn(types);

        assertEquals(types, badgeService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(badgeRepository.findByDbid(DBID)).thenReturn(badge);
        assertEquals(badge, badgeService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(badgeRepository.findByName(NAME)).thenReturn(badge);
        assertEquals(badge, badgeService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(badgeRepository.findByName(NAME)).thenReturn(null);
        when(badgeRepository.findFirstByNameStartingWith(NAME)).thenReturn(badge);
        assertEquals(badge, badgeService.findByName(NAME));
    }

    @Test
    public void create() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);

        Badge badge = badgeService.create(input);
        assertEquals(NAME, badge.getName());
        verify(badgeRepository, times(1)).save(badge);
    }

    @Test
    public void updateExistingRecord() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);

        when(badgeRepository.findByDbid(DBID)).thenReturn(badge);

        Badge badge1 = badgeService.update(input, DBID);
        assertEquals(badge, badge1);
        assertEquals(NAME, badge1.getName());
        verify(badgeRepository, times(1)).save(badge1);
    }

    @Test
    public void updateNonExistingRecord() {
        BadgeInputDto input = new BadgeInputDto();
        input.setName(NAME);

        when(badgeRepository.findByDbid(DBID)).thenReturn(null);

        Badge badge1 = badgeService.update(input, DBID);
        assertNull(badge1);
        verify(badgeRepository, times(0)).save(ArgumentMatchers.any());
    }

    @Test
    public void delete() {
        badgeService.delete(DBID);
        verify(badgeRepository, times(1)).deleteByDbid(captor.capture());
        assertEquals(DBID, captor.getValue());
    }

}