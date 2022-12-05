package com.pokemonurpg.configuration.v1.pokemon.species.input;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.pokemonurpg.entities.v1.pokemon.Species;
import com.pokemonurpg.lib.input.v1.FilterableGetParams;

public class SpeciesGetParams extends FilterableGetParams<Species> {
    Boolean ownableOnly = false;
    Boolean startersOnly = false;

    public Boolean getOwnableOnly() {
        return ownableOnly;
    }

    public void setOwnableOnly(Boolean ownableOnly) {
        this.ownableOnly = ownableOnly;
    }

    public Boolean getStartersOnly() {
        return startersOnly;
    }

    public void setStartersOnly(Boolean startersOnly) {
        this.startersOnly = startersOnly;
    }

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
    }
}
