package com.pokemonurpg.configuration.v1.pokemon.ability;

import com.pokemonurpg.configuration.v1.pokemon.PokemonViews;

public interface AbilityViews extends PokemonViews {
    public static interface Id extends AbilityViews {

    }
    public static interface Brief extends Id {

    }
    public static interface Full extends Brief {

    }
}
