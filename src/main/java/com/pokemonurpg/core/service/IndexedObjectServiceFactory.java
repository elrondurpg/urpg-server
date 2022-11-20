package com.pokemonurpg.core.service;

import com.pokemonurpg.configuration.v1.gym.gymownershipterm.service.GymOwnershipTermService;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexedObjectServiceFactory {

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public IndexedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "OwnedPokemon": return ownedPokemonService;
            }
        }
        return null;
    }

}
