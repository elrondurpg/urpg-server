package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.*;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestAttributeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestRankRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestGenerationRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.EarnedRibbonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EarnedRibbonServiceTest {
    private final static Integer DBID = 432;
    private final OwnedPokemon POKEMON = mock(OwnedPokemon.class);
    private final ContestRank RANK = mock(ContestRank.class);
    private final String RANK_NAME = "RANK_NAME";
    private final ContestAttribute ATTRIBUTE = mock(ContestAttribute.class);
    private final String ATTR_NAME = "ATTR_NAME";
    private final EarnedRibbon EXISTING_RECORD = mock(EarnedRibbon.class);
    private static final String GENERATION_NAME = "GENERATION_NAME";

    @InjectMocks
    private EarnedRibbonService earnedRibbonService;

    @Mock
    private EarnedRibbonRepository earnedRibbonRepository;

    @Mock
    private ContestRankRepository contestRankRepository;

    @Mock
    private ContestAttributeRepository contestAttributeRepository;

    @Mock
    private ContestGenerationRepository contestGenerationRepository;

    @Test
    public void createsNewRibbon() {
        ContestGeneration generation = new ContestGeneration();
        generation.setName(GENERATION_NAME);

        EarnedRibbonRequest input = new EarnedRibbonRequest();
        input.setRank(RANK_NAME);
        input.setAttribute(ATTR_NAME);
        input.setGeneration(GENERATION_NAME);

        when(contestRankRepository.findByName(RANK_NAME)).thenReturn(RANK);
        when(contestAttributeRepository.findByName(ATTR_NAME)).thenReturn(ATTRIBUTE);
        when(contestGenerationRepository.findByName(GENERATION_NAME)).thenReturn(generation);

        earnedRibbonService.update(input, POKEMON);
    }

    @Test
    public void updateExistingRibbon() {
        EarnedRibbonRequest input = new EarnedRibbonRequest();
        /*input.setDbid(DBID);

        when(earnedRibbonRepository.findByDbid(DBID)).thenReturn(EXISTING_RECORD);

        earnedRibbonService.update(input, POKEMON);

        verify(EXISTING_RECORD, times(1)).update(input);
        verify(earnedRibbonRepository, times(1)).save(EXISTING_RECORD);*/
    }

    @Test
    public void deleteExistingRibbon() {
        EarnedRibbonRequest input = new EarnedRibbonRequest();
        input.setDelete(true);

        // when(earnedRibbonRepository.findByDbid(DBID)).thenReturn(EXISTING_RECORD);

        // earnedRibbonService.update(input, POKEMON);

        // verify(earnedRibbonRepository, times(1)).delete(EXISTING_RECORD);
    }
}