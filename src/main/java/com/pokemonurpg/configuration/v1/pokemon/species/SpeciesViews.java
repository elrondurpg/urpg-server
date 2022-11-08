package com.pokemonurpg.configuration.v1.pokemon.species;

import com.pokemonurpg.configuration.v1.pokemon.PokemonViews;

public interface SpeciesViews extends PokemonViews {
    public static interface Id extends SpeciesViews {

    }
    public static interface Brief extends Id {

    }
    public static interface Full extends Brief {

    }
}