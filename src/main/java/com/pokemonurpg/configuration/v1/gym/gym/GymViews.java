package com.pokemonurpg.configuration.v1.gym.gym;

import com.pokemonurpg.configuration.v1.gym.GymSubdomainViews;

public interface GymViews extends GymSubdomainViews {
    public static interface Id extends GymViews {

    }
    public static interface Brief extends Id {
        
    }
    public static interface Full extends Brief {
        
    }
}
