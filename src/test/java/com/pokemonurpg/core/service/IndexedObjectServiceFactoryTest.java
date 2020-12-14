package com.pokemonurpg.core.service;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.LegendaryProgress;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import com.pokemonurpg.stats.service.LegendaryProgressService;
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
    private EarnedRibbonService earnedRibbonService;

    @Mock
    private GymService gymService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Test
    public void testAll() {
        assertEquals(earnedRibbonService, indexedObjectServiceFactory.getServiceForClass(EarnedRibbon.class));
        assertEquals(gymService, indexedObjectServiceFactory.getServiceForClass(Gym.class));
        assertEquals(legendaryProgressService, indexedObjectServiceFactory.getServiceForClass(LegendaryProgress.class));
    }
}