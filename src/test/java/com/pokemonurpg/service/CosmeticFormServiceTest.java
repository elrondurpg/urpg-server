package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.response.CosmeticFormDto;
import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.CosmeticForm;
import com.pokemonurpg.repository.CosmeticFormRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosmeticFormServiceTest {

    private CosmeticFormService cosmeticFormService;
    private CosmeticFormRepository cosmeticFormRepository = mock(CosmeticFormRepository.class);

    private CosmeticForm spikyEaredPikachu = TestObjectFactory.createSpikyEaredPikachu();

    @Before
    public void init() {
        cosmeticFormService = new CosmeticFormService(cosmeticFormRepository);
    }

    @Test
    public void serviceReturnsSpikyEaredPikachuInList() {
        int speciesDbid = TestObjectFactory.TEST_SPECIES_DBID;
        ArrayList<CosmeticForm> list = new ArrayList<>();
        list.add(spikyEaredPikachu);
        when(cosmeticFormRepository.findByIdSpeciesDbid(speciesDbid)).thenReturn(list);

        List<CosmeticFormDto> responseList = cosmeticFormService.findBySpeciesDbid(speciesDbid);
        assertNotNull(responseList);
        assertEquals(1, responseList.size());

        CosmeticFormDto cosmeticForm = responseList.get(0);
        assertEquals(spikyEaredPikachu.getDisplayName(), cosmeticForm.getDisplayName());
        assertEquals(spikyEaredPikachu.getMethod(), cosmeticForm.getMethod());
        assertEquals(spikyEaredPikachu.getName(), cosmeticForm.getName());
    }

    @Test
    public void serviceReturnsEmptyList() {
        List<CosmeticFormDto> responseList = cosmeticFormService.findBySpeciesDbid(-1);
        assertNotNull(responseList);
        assertEquals(0, responseList.size());
    }

}