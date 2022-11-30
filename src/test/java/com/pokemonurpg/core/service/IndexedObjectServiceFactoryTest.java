package com.pokemonurpg.core.service;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.member.member.model.LegendaryProgress;
import com.pokemonurpg.configuration.v1.member.member.service.LegendaryProgressService;
import com.pokemonurpg.configuration.v1.gym.gym.service.GymService;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IndexedObjectServiceFactoryTest {

    @InjectMocks
    private IndexedObjectServiceFactory indexedObjectServiceFactory;

    @Mock
    private EarnedRibbonService earnedRibbonService;

    @Mock
    private GymService gymService;

    @Mock
    private GymOwnershipTermService gymOwnershipTermService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private OwnedPokemonService ownedPokemonService;

    @Test
    public void testAll() {
        assertEquals(earnedRibbonService, indexedObjectServiceFactory.getServiceForClass(EarnedRibbon.class));
        assertEquals(gymService, indexedObjectServiceFactory.getServiceForClass(Gym.class));
        assertEquals(gymOwnershipTermService, indexedObjectServiceFactory.getServiceForClass(GymOwnershipTerm.class));
        assertEquals(legendaryProgressService, indexedObjectServiceFactory.getServiceForClass(LegendaryProgress.class));
        assertEquals(ownedPokemonService, indexedObjectServiceFactory.getServiceForClass(OwnedPokemon.class));
        assertNull(indexedObjectServiceFactory.getServiceForClass(Object.class));
    }
}