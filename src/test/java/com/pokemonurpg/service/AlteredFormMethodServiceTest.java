package com.pokemonurpg.service;

import com.pokemonurpg.factory.TestObjectFactory;
import com.pokemonurpg.object.pokemon.AlteredFormMethod;
import com.pokemonurpg.repository.AlteredFormMethodRepository;
import com.pokemonurpg.service.pokemon.AlteredFormMethodService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlteredFormMethodServiceTest {
    private AlteredFormMethodRepository alteredFormMethodRepository = mock(AlteredFormMethodRepository.class);
    private AlteredFormMethodService alteredFormMethodService;

    @Before
    public void init() {
        alteredFormMethodService = new AlteredFormMethodService(alteredFormMethodRepository);
    }

    @Test
    public void findByDexnoReturnsString() {
        AlteredFormMethod methodObj = new AlteredFormMethod();
        methodObj.setDexno(TestObjectFactory.TEST_SPECIES_DEXNO);
        methodObj.setMethod(TestObjectFactory.TEST_ALTERNATE_FORM_METHOD);
        when(alteredFormMethodRepository.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO)).thenReturn(methodObj);

        String method = alteredFormMethodService.findByDexno(TestObjectFactory.TEST_SPECIES_DEXNO);
        assertEquals(TestObjectFactory.TEST_ALTERNATE_FORM_METHOD, method);
    }

    @Test
    public void findByDexnoReturnsNull() {
        String method = alteredFormMethodService.findByDexno(-1);
        assertNull(method);
    }

}