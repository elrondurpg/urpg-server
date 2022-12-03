package com.pokemonurpg.configuration.v3.shared.gateway;

import org.springframework.data.domain.Pageable;

import com.pokemonurpg.configuration.v3.shared.request.GetParams;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactory;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.pokemonurpg.entities.v3.shared.UrpgRepository;

public abstract class GetPagedConfigurationJpaDataGateway
<
    IdView extends NamedEntityResponseView,
    BriefView extends IdView,
    FullView extends BriefView, 
    Request extends GetParams,
    JpaEntity extends FullView
>
implements GetPagedConfigurationDataGateway
<
    IdView,
    BriefView, 
    FullView, 
    Request
>
{
    protected JpaPageableFactory pageableFactory;
    protected UrpgRepository<JpaEntity, ?> repository;
    
    public GetPagedConfigurationJpaDataGateway(JpaPageableFactory pageableFactory,
            UrpgRepository<JpaEntity, ?> repository) {
        this.pageableFactory = pageableFactory;
        this.repository = repository;
    }

    @Override
    public PagedResponse<? extends IdView> getWithIdView(Request request) {
        return getWithBriefView(request);
    }

    @Override
    public PagedResponse<? extends BriefView> getWithBriefView(Request request) {
        return getWithFullView(request);
    }

    @Override
    public PagedResponse<? extends FullView> getWithFullView(Request request) {
        Pageable pageable = pageableFactory.fromRequest(request);
        return repository.findAll(pageable);
    }
}
