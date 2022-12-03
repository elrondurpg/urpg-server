package com.pokemonurpg.configuration.v3.pokemon.species.get;

import com.pokemonurpg.configuration.v3.shared.request.FilterableGetParams;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPagedSpeciesConfigurationRequest extends FilterableGetParams {
    Boolean ownableOnly = false;
    Boolean startersOnly = false;
/*
    public Example<Species> asExample() {
        return new ExampleBuilder()
            .withOwnable(ownableOnly)
            .withStartersOnly(startersOnly)
            .build();
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
    }*/
}
