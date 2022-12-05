package com.pokemonurpg.configuration.v1.shared.handler;

import org.springframework.data.domain.Pageable;

import com.pokemonurpg.configuration.v1.shared.request.JpaPageableFactory;
import com.pokemonurpg.configuration.v1.shared.request.ListRequest;
import com.pokemonurpg.configuration.v1.shared.view.ListResponse;
import com.pokemonurpg.entities.v1.shared.UrpgRepository;

public class ListHandler<EntityType extends ResponseType, RequestType extends ListRequest, ResponseType>
    implements ListInputBoundary<RequestType, ResponseType> 
{
    private UrpgRepository<EntityType, ?> repository;
    private JpaPageableFactory pageableFactory;

    public ListHandler(UrpgRepository<EntityType, ?> repository, JpaPageableFactory pageableFactory) {
        this.repository = repository;
        this.pageableFactory = pageableFactory;
    }

    public ListResponse<ResponseType> getList(RequestType request) {
        Pageable pageable = pageableFactory.fromRequest(request);
        return new ListResponse<>(repository.findAll(pageable));
    }
}
