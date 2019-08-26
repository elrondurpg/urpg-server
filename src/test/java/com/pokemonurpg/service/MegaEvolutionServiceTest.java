package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.response.MegaEvolutionDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.MegaEvolution;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.repository.MegaEvolutionRepository;
import com.pokemonurpg.repository.SpeciesRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MegaEvolutionServiceTest {

    private MegaEvolutionService megaEvolutionService;
    private MegaEvolutionRepository megaEvolutionRepository = mock(MegaEvolutionRepository.class);
    private SpeciesRepository speciesRepository = mock(SpeciesRepository.class);
    private SpeciesAbilityService speciesAbilityService;

    private MegaEvolution megaCharizardXRecord = TestObjectFactory.createMegaCharizardXRecord();
    private Species megaCharizardX = TestObjectFactory.createMegaCharizardX();

    @Before
    public void init() {
        megaEvolutionService = new MegaEvolutionService(megaEvolutionRepository, speciesRepository, speciesAbilityService);
    }

    @Test
    public void findByOriginalDbid() {
        when(megaEvolutionRepository.findByIdOriginalDbid(TestObjectFactory.TEST_MEGA_EVOLUTION_ORIGINAL_DBID)).thenReturn(Arrays.asList(megaCharizardXRecord));
        when(speciesRepository.findByDbid(megaCharizardXRecord.getId().getMegaEvolutionDbid())).thenReturn(megaCharizardX);

        List<MegaEvolutionDto> dtos = megaEvolutionService.findByOriginalDbid(TestObjectFactory.TEST_MEGA_EVOLUTION_ORIGINAL_DBID);
        assertEquals(1, dtos.size());
        assertEquals(megaCharizardX.getName(), dtos.get(0).getName());
    }

    @Test
    public void findByMegaDbid() {
        when(megaEvolutionRepository.findByIdMegaEvolutionDbid(TestObjectFactory.TEST_MEGA_EVOLUTION_DBID)).thenReturn(megaCharizardXRecord);

        boolean isMega = megaEvolutionService.isMegaEvolution(TestObjectFactory.TEST_MEGA_EVOLUTION_DBID);
        assertTrue(isMega);
    }
}