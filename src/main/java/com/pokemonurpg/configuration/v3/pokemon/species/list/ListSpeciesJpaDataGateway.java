package com.pokemonurpg.configuration.v3.pokemon.species.list;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.configuration.v3.shared.gateway.ListJpaDataGateway;
import com.pokemonurpg.configuration.v3.shared.request.JpaPageableFactory;
import com.pokemonurpg.entities.v1.pokemon.species.Species;
import com.pokemonurpg.entities.v1.pokemon.species.SpeciesRepository;
import com.pokemonurpg.entities.v1.shared.PagedEntity;

public class ListSpeciesJpaDataGateway extends ListJpaDataGateway
<
    ListSpeciesView,
    ListSpeciesRequest
>
{
    public ListSpeciesJpaDataGateway(JpaPageableFactory pageableFactory, SpeciesRepository repository) {
        super(pageableFactory, repository);
    }

    @Override
    public PagedEntity<ListSpeciesView> getList(ListSpeciesRequest request) {
        SpeciesRepository repo = (SpeciesRepository) repository;
        Example<Species> example = new ExampleBuilder()
            .withOwnable(request.getOwnableOnly())
            .withStartersOnly(request.getStartersOnly())
            .build();
        Pageable pageable = pageableFactory.fromRequest(request);
        return new PagedEntity<ListSpeciesView> (repo.findAll(example, pageable));
    }
    
    private static class ExampleBuilder {
        Species example = new Species();
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

        private Example<Species> build() {
            return Example.of(example, matcher);
        }
    }
}
