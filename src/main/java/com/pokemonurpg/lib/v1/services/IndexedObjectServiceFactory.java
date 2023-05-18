package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.configuration.v1.gymleaderrecords.GymLeaderRecordService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.stats.v1.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexedObjectServiceFactory {

    @Resource
    private GymLeaderRecordService gymLeaderRecordService;

    @Resource
    private GymService gymService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public IndexedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "Gym": return gymService;
                case "GymLeaderRecord": return gymLeaderRecordService;
                case "OwnedPokemon": return ownedPokemonService;
            }
        }
        return null;
    }

}
