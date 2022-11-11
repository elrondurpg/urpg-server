package com.pokemonurpg.contest.service;

import com.pokemonurpg.contest.input.ContestRankInputDto;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.repository.ContestRankRepository;
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
public class ContestRankServiceTest {
    private final static ContestRank    CONTEST_RANK = mock(ContestRank.class);
    private final static Integer DBID = 32432;
    private final static String NAME = "TEST";

    @InjectMocks
    private ContestRankService contestRankService;

    @Mock
    private ContestRankRepository contestRankRepository;

    private ContestRank contestRank = new ContestRank();

    @Test
    public void findAllNamesReturnsValueFromRepository() {
        List<String> types = new ArrayList<>();
        when(contestRankRepository.findAllNames()).thenReturn(types);

        assertEquals(types, contestRankService.findAllNames());
    }

    @Test
    public void findByDbid() {
        when(contestRankRepository.findByDbid(DBID)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankService.findByDbid(DBID));
    }

    @Test
    public void findByNameExactMatch() {
        when(contestRankRepository.findByName(NAME)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankService.findByName(NAME));
    }

    @Test
    public void findByNameNotExactMatch() {
        when(contestRankRepository.findByName(NAME)).thenReturn(null);
        when(contestRankRepository.findFirstByNameStartingWith(NAME)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankService.findByName(NAME));
    }

    @Test
    public void findByNameExact() {
        when(contestRankRepository.findByName(NAME)).thenReturn(CONTEST_RANK);
        assertEquals(CONTEST_RANK, contestRankService.findByNameExact(NAME));
    }

    @Test
    public void create() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);

        ContestRank contestRank = contestRankService.create(input);
        assertEquals(NAME, contestRank.getName());
        verify(contestRankRepository, times(1)).save(contestRank);
    }

    @Test
    public void updateExistingRecord() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);

        when(contestRankRepository.findByDbid(DBID)).thenReturn(contestRank);

        ContestRank contestRank1 = contestRankService.update(input, DBID);
        assertEquals(contestRank, contestRank1);
        assertEquals(NAME, contestRank1.getName());
        verify(contestRankRepository, times(1)).save(contestRank1);
    }

    @Test
    public void updateNonExistingRecord() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);

        when(contestRankRepository.findByDbid(DBID)).thenReturn(null);

        ContestRank contestRank1 = contestRankService.update(input, DBID);
        assertNull(contestRank1);
        verify(contestRankRepository, times(0)).save(ArgumentMatchers.any());
    }

}