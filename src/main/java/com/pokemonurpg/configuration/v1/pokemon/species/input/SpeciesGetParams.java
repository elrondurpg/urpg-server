package com.pokemonurpg.configuration.v1.pokemon.species.input;

import org.springframework.data.domain.Example;

import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.lib.v1.FilterableGetParams;

public class SpeciesGetParams extends FilterableGetParams<Species> {
    Boolean ownableOnly = false;

    public Boolean getOwnable() {
        return ownableOnly;
    }

    public void setOwnableOnly(Boolean ownableOnly) {
        this.ownableOnly = ownableOnly;
    }

    public Example<Species> asExample() {
        return new ExampleBuilder()
            .withOwnable(ownableOnly)
            .build();
    }

    private static class ExampleBuilder {
        Species example = new Species();

        private ExampleBuilder withOwnable(Boolean ownableOnly) {
            if (ownableOnly) {
                example.setPreMega(null);
                example.setBattleOnly(false);
            }
            return this;
        }

        private Example<Species> build() {
            return Example.of(example);
        }
    }
}
