package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.*;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeService;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestAttributeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestRankRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestTypeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.EarnedRibbonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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
    private ContestTypeRepository contestTypeRepository;

    @Test
    public void createsNewRibbon() {
        ContestType generation = new ContestType();
        generation.setName(GENERATION_NAME);

        EarnedRibbonInputDto input = new EarnedRibbonInputDto();
        input.setRank(RANK_NAME);
        input.setAttribute(ATTR_NAME);
        input.setGeneration(GENERATION_NAME);

        when(contestRankRepository.findByName(RANK_NAME)).thenReturn(RANK);
        when(contestAttributeRepository.findByName(ATTR_NAME)).thenReturn(ATTRIBUTE);
        when(contestTypeRepository.findByName(GENERATION_NAME)).thenReturn(generation);

        earnedRibbonService.update(input, POKEMON);
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