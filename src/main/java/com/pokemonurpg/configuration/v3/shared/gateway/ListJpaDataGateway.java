package com.pokemonurpg.configuration.v3.shared.gateway;

import org.springframework.data.domain.Pageable;

import com.pokemonurpg.configuration.v3.shared.request.ListRequest;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactory;
import com.pokemonurpg.entities.v3.shared.PagedEntity;
import com.pokemonurpg.entities.v3.shared.UrpgRepository;

public abstract class ListJpaDataGateway
<
    View, 
    Request extends ListRequest
>
implements ListDataGateway
<
    View, 
    Request
>
{
    protected JpaPageableFactory pageableFactory;
    protected UrpgRepository<? extends View, ?> repository;
    
    public ListJpaDataGateway(JpaPageableFactory pageableFactory,
            UrpgRepository<? extends View, ?> repository) {
        this.pageableFactory = pageableFactory;
        this.repository = repository;
    }

    @Override
    public PagedEntity<View> getList(Request request) {
        Pageable pageable = pageableFactory.fromRequest(request);
        return new PagedEntity<View> (repository.findAll(pageable));
    }
}
