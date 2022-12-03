package com.pokemonurpg.configuration.v3.pokemon.species.get;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesBriefResponseView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseView;
import com.pokemonurpg.configuration.v3.shared.gateway.GetPagedConfigurationDataGateway;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public class ListSpeciesInteractor implements GetPagedSpeciesConfigurationInputBoundary {

    private GetPagedConfigurationDataGateway<
        NamedEntityResponseView,
        SpeciesBriefResponseView,
        SpeciesFullResponseView,
        GetPagedSpeciesConfigurationRequest
    > gateway;

    public ListSpeciesInteractor(
            GetPagedConfigurationDataGateway<
                NamedEntityResponseView, 
                SpeciesBriefResponseView, 
                SpeciesFullResponseView, 
                GetPagedSpeciesConfigurationRequest> gateway) {
        this.gateway = gateway;
    }

    @Override
    public PagedResponse<? extends NamedEntityResponseView> get(GetPagedSpeciesConfigurationRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
