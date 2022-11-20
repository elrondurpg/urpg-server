package com.pokemonurpg.configuration.v1.attack.target.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.attack.target.AttackTargetTypeViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "attack_target_type")
@Getter
@Setter
@JsonView(value = { AttackTargetTypeViews.Brief.class, AttackViews.Full.class, SpeciesViews.Full.class })
public class AttackTargetType extends NamedConfigurationModel {

    @Column
    private String description;
}
