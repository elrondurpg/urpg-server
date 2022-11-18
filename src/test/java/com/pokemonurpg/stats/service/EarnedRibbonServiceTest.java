package com.pokemonurpg.stats.service;

import com.pokemonurpg.configuration.v1.contest.attribute.model.ContestAttribute;
import com.pokemonurpg.configuration.v1.contest.rank.model.ContestRank;
import com.pokemonurpg.configuration.v1.contest.attribute.service.ContestAttributeService;
import com.pokemonurpg.configuration.v1.contest.rank.service.ContestRankService;
import com.pokemonurpg.stats.input.EarnedRibbonInputDto;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.EarnedRibbonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EarnedRibbonServiceTest {
    private final static Integer DBID = 432;
    private final OwnedPokemon POKEMON = mock(OwnedPokemon.class);
    private final ContestRank RANK = mock(ContestRank.class);
    private final String RANK_NAME = "RANK_NAME";
    private final ContestAttribute ATTRIBUTE = mock(ContestAttribute.class);
    private final String ATTR_NAME = "ATTR_NAME";
    private final EarnedRibbon EXISTING_RECORD = mock(EarnedRibbon.class);

    @InjectMocks
    private EarnedRibbonService earnedRibbonService;

    @Mock
    private EarnedRibbonRepository earnedRibbonRepository;

    @Mock
    private ContestRankService contestRankService;

    @Mock
    private ContestAttributeService contestAttributeService;

    @Test
    public void createsNewRibbon() {
        EarnedRibbonInputDto input = new EarnedRibbonInputDto();
        input.setRank(RANK_NAME);
        input.setAttribute(ATTR_NAME);

        when(contestRankService.findByName(RANK_NAME)).thenReturn(RANK);
        when(contestAttributeService.findByName(ATTR_NAME)).thenReturn(ATTRIBUTE);

        earnedRibbonService.update(input, POKEMON);

        verify(earnedRibbonRepository, times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void updateExistingRibbon() {
        EarnedRibbonInputDto input = new EarnedRibbonInputDto();
        /*input.setDbid(DBID);

        when(earnedRibbonRepository.findByDbid(DBID)).thenReturn(EXISTING_RECORD);

        earnedRibbonService.update(input, POKEMON);

        verify(EXISTING_RECORD, times(1)).update(input);
        verify(earnedRibbonRepository, times(1)).save(EXISTING_RECORD);*/
    }

    @Test
    public void deleteExistingRibbon() {
        EarnedRibbonInputDto input = new EarnedRibbonInputDto();
        input.setDelete(true);

        // when(earnedRibbonRepository.findByDbid(DBID)).thenReturn(EXISTING_RECORD);

        // earnedRibbonService.update(input, POKEMON);

        // verify(earnedRibbonRepository, times(1)).delete(EXISTING_RECORD);
    }
}