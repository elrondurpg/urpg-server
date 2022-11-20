package com.pokemonurpg.configuration.v1.gym.champion;

import com.pokemonurpg.configuration.v1.gym.GymViews;

public interface ChampionViews extends GymViews {
    public static interface Id extends ChampionViews {

    }
    public static interface Brief extends Id {

    }
    public static interface Full extends Brief {

    }
}
