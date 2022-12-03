package com.pokemonurpg.configuration.v3.pokemon.species.get;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesBriefResponseView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseView;
import com.pokemonurpg.configuration.v3.shared.gateway.GetPagedConfigurationJpaDataGateway;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactory;
import com.pokemonurpg.configuration.v3.shared.response.PagedResponse;
import com.pokemonurpg.configuration.v3.shared.view.NamedEntityResponseView;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesEntity;
import com.pokemonurpg.entities.v3.pokemon.species.SpeciesRepository;

public class GetPagedSpeciesConfigurationJpaDataGateway extends GetPagedConfigurationJpaDataGateway
<
    NamedEntityResponseView,
    SpeciesBriefResponseView,
    SpeciesFullResponseView,
    GetPagedSpeciesConfigurationRequest,
    SpeciesEntity
>
{
    public GetPagedSpeciesConfigurationJpaDataGateway(JpaPageableFactory pageableFactory, SpeciesRepository repository) {
        super(pageableFactory, repository);
    }

    @Override
    public PagedResponse<? extends SpeciesFullResponseView> getWithFullView(GetPagedSpeciesConfigurationRequest request) {
        Example<SpeciesEntity> example = new ExampleBuilder()
            .withOwnable(request.getOwnableOnly())
            .withStartersOnly(request.getStartersOnly())
            .build();
        Pageable pageable = pageableFactory.fromRequest(request);
        return repository.findAll(example, pageable);
    }
    
    private static class ExampleBuilder {
        SpeciesEntity example = new SpeciesEntity();
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        private ExampleBuilder withOwnable(Boolean ownableOnly) {
            if (ownableOnly) {
                example.setMegaEvolved(false);
                example.setBattleOnly(false);
            }
            return this;
        }

        private ExampleBuilder withStartersOnly(Boolean startersOnly) {
            if (startersOnly) {
                example.setBattleOnly(false);
                example.setEvolved(false);
                example.setLegendaryTier(0);
                example.setMegaEvolved(false);
                example.setFullyEvolved(false);
            }
            return this;
        }

        private Example<SpeciesEntity> build() {
            return Example.of(example, matcher);
        }
    }
}
