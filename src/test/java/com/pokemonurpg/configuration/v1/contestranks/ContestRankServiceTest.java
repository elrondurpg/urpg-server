package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.configuration.v1.contestranks.ContestRankInputDto;
import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestRankRepository;
import com.pokemonurpg.configuration.v1.contestranks.ContestRankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
    }

}