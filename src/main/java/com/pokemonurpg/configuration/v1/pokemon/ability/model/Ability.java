package com.pokemonurpg.configuration.v1.pokemon.ability.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.model.SpeciesAbility;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonView(value = { AbilityViews.Full.class })
@Getter
@Setter
public class Ability extends NamedConfigurationModel {

    @JsonView(value = { AbilityViews.Brief.class, SpeciesViews.Full.class })
    @Column
    private String description;

    @OneToMany(mappedBy = "ability")
    private Set<SpeciesAbility> pokemon;

    public Ability() {}
}
