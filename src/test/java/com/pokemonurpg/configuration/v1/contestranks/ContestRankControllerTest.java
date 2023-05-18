package com.pokemonurpg.configuration.v1.contestranks;

import com.pokemonurpg.configuration.v1.contestranks.ContestRankController;
import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.configuration.v1.contestranks.ContestRankInputDto;
import com.pokemonurpg.configuration.v1.contestranks.ContestRankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContestRankControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;

    @InjectMocks
    private ContestRankController contestRankController;

    @Mock
    private ContestRankService contestRankService;

    private ContestRank contestRank = new ContestRank();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(contestRankService.findAllNames()).thenReturn(names);
        assertEquals(names, contestRankController.findAllNames());
    }

    @Test
    public void findByName() {
        when(contestRankService.findByName(NAME)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankController.findByName(NAME));
    }

    @Test
    public void create() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);
        when(contestRankService.create(input)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankController.create(input));
    }

    @Test
    public void update() {
        ContestRankInputDto input = new ContestRankInputDto();
        input.setName(NAME);
        when(contestRankService.update(input, DBID)).thenReturn(contestRank);
        assertEquals(contestRank, contestRankController.update(input, DBID));
    }

}