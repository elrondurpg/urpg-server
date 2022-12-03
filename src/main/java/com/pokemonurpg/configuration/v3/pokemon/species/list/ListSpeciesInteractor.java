package com.pokemonurpg.configuration.v3.pokemon.species.list;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.gateway.ListDataGateway;
import com.pokemonurpg.configuration.v3.shared.interactor.ListInteractor;

public class ListSpeciesInteractor extends ListInteractor<ListSpeciesView, ListSpeciesRequest> {

    public ListSpeciesInteractor(ListDataGateway<ListSpeciesView, ListSpeciesRequest> gateway) {
        super(gateway);
    }
    
}
