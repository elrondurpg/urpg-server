package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeaderRecord;
import com.pokemonurpg.configuration.v1.gymleaderrecords.GymLeaderRecordService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.stats.v1.LegendaryProgressService;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexedObjectServiceFactoryTest {

    @InjectMocks
    private IndexedObjectServiceFactory indexedObjectServiceFactory;

    @Mock
    private GymService gymService;

    @Mock
    private GymLeaderRecordService gymLeaderRecordService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void testAll() {
        assertEquals(gymService, indexedObjectServiceFactory.getServiceForClass(Gym.class));
        assertEquals(gymLeaderRecordService, indexedObjectServiceFactory.getServiceForClass(GymLeaderRecord.class));
        assertEquals(ownedPokemonService, indexedObjectServiceFactory.getServiceForClass(OwnedPokemon.class));
        assertNull(indexedObjectServiceFactory.getServiceForClass(Object.class));
    }
}