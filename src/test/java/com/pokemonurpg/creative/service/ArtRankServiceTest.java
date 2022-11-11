package com.pokemonurpg.creative.service;

import com.pokemonurpg.creative.input.ArtRankInputDto;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.repository.ArtRankRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtRankServiceTest {
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ArtRankService artRankService;

    @Mock
    private ArtRankRepository artRankRepository;

    private ArtRank artRank = new ArtRank();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(artRankRepository.findAllNames()).thenReturn(types);

        assertEquals(types, artRankService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(artRankRepository.findByDbid(DBID)).thenReturn(artRank);
        assertEquals(artRank, artRankService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(artRankRepository.findByName(NAME)).thenReturn(artRank);
        assertEquals(artRank, artRankService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(artRankRepository.findByName(NAME)).thenReturn(null);
        when(artRankRepository.findFirstByNameStartingWith(NAME)).thenReturn(artRank);
        assertEquals(artRank, artRankService.findByName(NAME));
    }

    @Test
    public void create() {
        ArtRankInputDto input = new ArtRankInputDto();
        input.setName(NAME);

        ArtRank artRank = artRankService.create(input);
        assertEquals(NAME, artRank.getName());
        verify(artRankRepository, times(1)).save(artRank);
    }

    @Test
    public void updateExistingRecord() {
        ArtRankInputDto input = new ArtRankInputDto();
        input.setName(NAME);

        when(artRankRepository.findByDbid(DBID)).thenReturn(artRank);

        ArtRank artRank1 = artRankService.update(input, DBID);
        assertEquals(artRank, artRank1);
        assertEquals(NAME, artRank1.getName());
        verify(artRankRepository, times(1)).save(artRank1);
    }

    @Test
    public void updateNonExistingRecord() {
        ArtRankInputDto input = new ArtRankInputDto();
        input.setName(NAME);

        when(artRankRepository.findByDbid(DBID)).thenReturn(null);

        ArtRank artRank1 = artRankService.update(input, DBID);
        assertNull(artRank1);
        verify(artRankRepository, times(0)).save(ArgumentMatchers.any());
    }

}