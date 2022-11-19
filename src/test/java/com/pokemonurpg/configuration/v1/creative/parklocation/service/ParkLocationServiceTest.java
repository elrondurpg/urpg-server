package com.pokemonurpg.configuration.v1.creative.parklocation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.creative.parklocation.model.ParkLocation;
import com.pokemonurpg.configuration.v1.creative.parklocation.repository.ParkLocationRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParkLocationServiceTest {

    @InjectMocks
    private ParkLocationService service;

    @Mock
    private ParkLocationRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(ParkLocation.class, service.getModelClass());
    }

}