package com.pokemonurpg.core.service;

import com.pokemonurpg.gym.service.GymOwnershipTermService;
import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexedObjectServiceFactory {

    @Resource
    private EarnedRibbonService earnedRibbonService;

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @Resource
    private GymService gymService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public IndexedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "EarnedRibbon": return earnedRibbonService;
                case "Gym": return gymService;
                case "GymOwnershipTerm": return gymOwnershipTermService;
                case "OwnedPokemon": return ownedPokemonService;
            }
        }
        return null;
    }

}
