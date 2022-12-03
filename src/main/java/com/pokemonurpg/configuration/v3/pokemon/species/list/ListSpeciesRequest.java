package com.pokemonurpg.configuration.v3.pokemon.species.list;

import com.pokemonurpg.configuration.v3.shared.request.ListRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListSpeciesRequest extends ListRequest {
    Boolean ownableOnly = false;
    Boolean startersOnly = false;
}
