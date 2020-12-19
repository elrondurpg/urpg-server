package com.pokemonurpg.core.service;

import com.pokemonurpg.gym.service.GymService;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import com.pokemonurpg.stats.service.LegendaryProgressService;
import com.pokemonurpg.stats.service.OwnedPokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexedObjectServiceFactory {

    @Resource
    private EarnedRibbonService earnedRibbonService;

    @Resource
    private GymService gymService;

    @Resource
    private LegendaryProgressService legendaryProgressService;

    @Resource
    private OwnedPokemonService ownedPokemonService;

    public IndexedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "EarnedRibbon": return earnedRibbonService;
                case "Gym": return gymService;
                case "LegendaryProgress": return legendaryProgressService;
                case "OwnedPokemon": return ownedPokemonService;
            }
        }
        return null;
    }

}
