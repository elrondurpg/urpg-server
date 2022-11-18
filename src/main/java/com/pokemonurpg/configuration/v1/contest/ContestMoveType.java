package com.pokemonurpg.configuration.v1.contest;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@JsonView(value = { ContestMoveTypeViews.Brief.class, AttackViews.Full.class, SpeciesViews.Full.class })
@Getter
@Setter
public class ContestMoveType extends NamedConfigurationModel {

    @Column
    private String description;

    @Column
    private Integer score;

    @Column
    private Integer jam;
}
