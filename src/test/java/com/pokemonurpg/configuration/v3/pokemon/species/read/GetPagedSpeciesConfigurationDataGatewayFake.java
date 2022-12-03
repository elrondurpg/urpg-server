package com.pokemonurpg.configuration.v3.pokemon.species.read;

import com.pokemonurpg.configuration.v3.pokemon.species.get.GetPagedSpeciesConfigurationRequest;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesBriefResponseView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseView;
import com.pokemonurpg.configuration.v3.shared.gateway.GetPagedConfigurationDataGateway;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;

public class GetPagedSpeciesConfigurationDataGatewayFake implements GetPagedConfigurationDataGateway
<
    NamedEntityResponseView,
    SpeciesBriefResponseView,
    SpeciesFullResponseView,
    GetPagedSpeciesConfigurationRequest
> 
{

    @Override
    public PagedResponse<? extends NamedEntityResponseView> getWithIdView(GetPagedSpeciesConfigurationRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PagedResponse<? extends SpeciesBriefResponseView> getWithBriefView(
            GetPagedSpeciesConfigurationRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PagedResponse<? extends SpeciesFullResponseView> getWithFullView(
            GetPagedSpeciesConfigurationRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
