package com.pokemonurpg.lib.v1.service;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymOwnershipTermService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexedObjectServiceFactory {

    @Resource
    private GymOwnershipTermService gymOwnershipTermService;

    @Resource
    private GymService gymService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public IndexedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "Gym": return gymService;
                case "GymOwnershipTerm": return gymOwnershipTermService;
                case "OwnedPokemon": return ownedPokemonService;
            }
        }
        return null;
    }

}
